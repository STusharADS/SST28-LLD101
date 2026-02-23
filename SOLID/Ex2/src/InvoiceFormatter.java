public class InvoiceFormatter {
    public String format(BillSummary b) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(b.invoiceId).append("\n");
        for (BillLine line : b.lines) {
            sb.append(String.format("- %s x%d = %.2f\n", line.name, line.qty, line.lineTotal));
        }
        sb.append(String.format("Subtotal: %.2f\n", b.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", b.taxPct, b.tax));
        sb.append(String.format("Discount: -%.2f\n", b.discount));
        sb.append(String.format("TOTAL: %.2f\n", b.total));
        return sb.toString();
    }
}
