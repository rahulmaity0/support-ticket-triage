package com.portfolio.supporttriage;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class TicketListener {

    private final TicketAnalyzerService ticketAnalyzerService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "incoming-tickets", groupId = "triage-group")
    public void consumeTicket(String ticketJson) {
        try {
            Ticket ticket = objectMapper.readValue(ticketJson, Ticket.class);
            ticketAnalyzerService.processTicket(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
