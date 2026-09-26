# Support Ticket Triage (Event-Driven Spring AI)

An event-driven AI support ticket triaging and analysis microservice built with **Spring Boot 4.1**, **Spring AI**, **Apache Kafka**, and **PostgreSQL**.

## 🚀 Key Features
- **Event-Driven Processing**: Asynchronously consumes incoming customer support tickets from an Apache Kafka topic (`incoming-tickets`) using `@KafkaListener`.
- **Automated AI Classification**: Leverages Spring AI's `ChatClient` with OpenAI to categorize tickets (`BILLING`, `TECHNICAL`, `GENERAL`) and detect customer sentiment (`POSITIVE`, `NEGATIVE`, `NEUTRAL`).
- **State Management**: Updates ticket lifecycle status to `PROCESSED` and persists enriched ticket details into PostgreSQL via Spring Data JPA.

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot 4.1**
- **Spring AI 2.0** (OpenAI Starter)
- **Spring Kafka** (`spring-boot-starter-kafka`)
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **Maven** & **Lombok**

## ⚙️ Configuration
Set your Kafka broker, PostgreSQL, and OpenAI credentials in `src/main/resources/application.properties`:
```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=triage-group
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.ai.openai.api-key=${OPENAI_API_KEY}
```

## 🏗️ Build and Run
```bash
./mvnw clean install
./mvnw spring-boot:run
```
