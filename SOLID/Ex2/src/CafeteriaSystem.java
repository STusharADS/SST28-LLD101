import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final TaxPolicy      taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceStore   store;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(TaxPolicy taxPolicy,
                           DiscountPolicy discountPolicy,
                           InvoiceStore store,
                           InvoiceFormatter formatter) {
        this.taxPolicy      = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.store          = store;
        this.formatter      = formatter;
    }

    public void addToMenu(MenuItem item) { menu.put(item.id, item); }

    public void checkout(List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        List<BillLine> billLines = new ArrayList<>();
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            billLines.add(new BillLine(item.name, l.qty, lineTotal));
        }

        double taxPct   = taxPolicy.taxPercent();
        double tax      = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(subtotal, lines.size());
        double total    = subtotal + tax - discount;

        BillSummary summary = new BillSummary(
            invId, billLines, subtotal, taxPct, tax, discount, total);

        String text = formatter.format(summary);
        System.out.print(text);

        store.save(invId, text);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
