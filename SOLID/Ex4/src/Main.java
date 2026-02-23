import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        BookingRequest req = new BookingRequest(
            LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        HostelFeeCalculator calc = new HostelFeeCalculator(
            roomPricingFor(req.roomType),
            addOnPricingsFor(req.addOns),
            new FakeBookingRepo()
        );
        calc.process(req);
    }

    private static RoomPricing roomPricingFor(int roomType) {
        return switch (roomType) {
            case LegacyRoomTypes.SINGLE -> new SingleRoom();
            case LegacyRoomTypes.DOUBLE -> new DoubleRoom();
            case LegacyRoomTypes.TRIPLE -> new TripleRoom();
            default                     -> new DeluxeRoom();
        };
    }

    private static List<AddOnPricing> addOnPricingsFor(List<AddOn> addOns) {
        List<AddOnPricing> result = new ArrayList<>();
        for (AddOn a : addOns) {
            switch (a) {
                case MESS    -> result.add(new MessAddOnPricing());
                case LAUNDRY -> result.add(new LaundryAddOnPricing());
                case GYM     -> result.add(new GymAddOnPricing());
            }
        }
        return result;
    }
}
