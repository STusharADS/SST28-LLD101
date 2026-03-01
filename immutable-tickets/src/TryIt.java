import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;
import java.util.List;

/**
 * Demo showing immutability in action.
 * 
 * After refactor:
 * - Direct mutation no longer compiles (no setters)
 * - External modifications to tags have no effect (defensive copy)
 * - Service "updates" return NEW ticket instances
 */
public class TryIt {

    public static void main(String[] args) {
        System.out.println("=== Immutable Ticket Demo ===\n");
        
        TicketService service = new TicketService();

        // Create initial ticket
        IncidentTicket ticket1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("1. Created ticket:");
        System.out.println(ticket1);
        System.out.println();

        // Assign ticket - returns NEW instance
        IncidentTicket ticket2 = service.assign(ticket1, "agent@example.com");
        System.out.println("2. After assign (NEW ticket created):");
        System.out.println("   Original ticket1: " + ticket1.getAssigneeEmail());
        System.out.println("   New ticket2:      " + ticket2.getAssigneeEmail());
        System.out.println();

        // Escalate ticket - returns NEW instance
        IncidentTicket ticket3 = service.escalateToCritical(ticket2);
        System.out.println("3. After escalate (NEW ticket created):");
        System.out.println("   Original ticket2 priority: " + ticket2.getPriority());
        System.out.println("   Original ticket2 tags:     " + ticket2.getTags());
        System.out.println("   New ticket3 priority:      " + ticket3.getPriority());
        System.out.println("   New ticket3 tags:          " + ticket3.getTags());
        System.out.println();

        // Try to modify tags list - will throw exception (unmodifiable)
        System.out.println("4. Attempting external tag mutation...");
        try {
            List<String> tags = ticket3.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("   ERROR: Should not reach here!");
        } catch (UnsupportedOperationException e) {
            System.out.println("   ✓ Success! Tags list is unmodifiable: " + e.getClass().getSimpleName());
        }
        System.out.println("   ticket3 tags unchanged: " + ticket3.getTags());
        System.out.println();

        // Demonstrate builder pattern directly
        System.out.println("5. Building a custom ticket with Builder:");
        IncidentTicket customTicket = IncidentTicket.builder()
                .id("CUSTOM-42")
                .reporterEmail("user@company.org")
                .title("Database connection timeout")
                .description("Unable to connect to prod DB")
                .priority("HIGH")
                .addTag("URGENT")
                .addTag("DATABASE")
                .slaMinutes(120)
                .source("WEBHOOK")
                .customerVisible(true)
                .build();
        System.out.println(customTicket);
        System.out.println();

        // Demonstrate toBuilder for updates
        System.out.println("6. Using toBuilder() for updates:");
        IncidentTicket updatedTicket = customTicket.toBuilder()
                .addTag("RESOLVED")
                .slaMinutes(60)
                .build();
        System.out.println("   Original: " + customTicket.getTags() + " (SLA=" + customTicket.getSlaMinutes() + ")");
        System.out.println("   Updated:  " + updatedTicket.getTags() + " (SLA=" + updatedTicket.getSlaMinutes() + ")");
        System.out.println();

        System.out.println("=== Immutability Verified! ===");
        System.out.println("✓ No setters available");
        System.out.println("✓ Tags list is unmodifiable");
        System.out.println("✓ Updates create new instances");
        System.out.println("✓ Validation centralized in Builder.build()");
    }
}
