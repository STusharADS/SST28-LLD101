public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    // Contract: n must be non-null. Always returns a non-null SendResult. Never throws.
    public abstract SendResult send(Notification n);
}
