package com.example.tickets;

/**
 * Service layer that creates tickets using immutable pattern.
 * Any "updates" create new ticket instances instead of mutating.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // Validation now happens centrally in Builder.build()
        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket original) {
        // Returns a NEW ticket with updated priority and tag
        // Original ticket remains unchanged (immutable)
        return original.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket original, String assigneeEmail) {
        // Returns a NEW ticket with updated assignee
        // Validation happens in Builder.build()
        return original.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
