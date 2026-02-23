import java.util.*;

public class BillSummary {
    public final String          invoiceId;
    public final List<BillLine>  lines;
    public final double          subtotal;
    public final double          taxPct;
    public final double          tax;
    public final double          discount;
    public final double          total;

    public BillSummary(String invoiceId, List<BillLine> lines,
                       double subtotal, double taxPct,
                       double tax, double discount, double total) {
        this.invoiceId = invoiceId;
        this.lines     = Collections.unmodifiableList(lines);
        this.subtotal  = subtotal;
        this.taxPct    = taxPct;
        this.tax       = tax;
        this.discount  = discount;
        this.total     = total;
    }
}
