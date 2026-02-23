import java.util.*;

public class HostelFeeCalculator {
    private final RoomPricing        roomPricing;
    private final List<AddOnPricing> addOnPricings;
    private final FakeBookingRepo    repo;
    private static final Money       DEPOSIT = new Money(5000.0);

    public HostelFeeCalculator(RoomPricing roomPricing,
                               List<AddOnPricing> addOnPricings,
                               FakeBookingRepo repo) {
        this.roomPricing   = roomPricing;
        this.addOnPricings = addOnPricings;
        this.repo          = repo;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly();
        ReceiptPrinter.print(req, monthly, DEPOSIT);
        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, DEPOSIT);
    }

    private Money calculateMonthly() {
        double total = roomPricing.monthlyBase();
        for (AddOnPricing a : addOnPricings) {
            total += a.monthlyFee();
        }
        return new Money(total);
    }
}
