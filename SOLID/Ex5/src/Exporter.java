public abstract class Exporter {
    // Contract: req must be non-null. Always returns a non-null ExportResult. Never throws.
    public abstract ExportResult export(ExportRequest req);
}
