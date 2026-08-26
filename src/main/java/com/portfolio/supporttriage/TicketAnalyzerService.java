package com.portfolio.supporttriage;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TicketAnalyzerService {

    private final ChatClient chatClient;
    private final TicketRepository ticketRepository;

    public TicketAnalyzerService(ChatClient.Builder chatClientBuilder, TicketRepository ticketRepository) {
        this.chatClient = chatClientBuilder.build();
        this.ticketRepository = ticketRepository;
    }

    public void processTicket(Ticket ticket) {
        log.info("Processing ticket id: {}", ticket.getId());
        
        String prompt = "Analyze this support ticket. Return the category (e.g. BILLING, TECHNICAL, GENERAL) and sentiment (POSITIVE, NEGATIVE, NEUTRAL) separated by a comma. Ticket: " + ticket.getContent();
        
        String aiResponse = chatClient.prompt()
                .user(prompt)
                .call()
                .content();
                
        try {
            String[] parts = aiResponse.split(",");
            if (parts.length >= 2) {
                ticket.setCategory(parts[0].trim());
                ticket.setSentiment(parts[1].trim());
            } else {
                ticket.setCategory("UNKNOWN");
                ticket.setSentiment("UNKNOWN");
            }
        } catch (Exception e) {
            log.error("Failed to parse AI response: {}", aiResponse, e);
            ticket.setCategory("ERROR");
        }
        
        ticket.setStatus("PROCESSED");
        ticketRepository.save(ticket);
        log.info("Finished processing ticket: {}", ticket);
    }
}
