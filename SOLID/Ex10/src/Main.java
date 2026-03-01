public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        
        // Dependency injection: create dependencies and inject them
        DistanceService distanceService = new DistanceCalculator();
        DriverService driverService = new DriverAllocator();
        PaymentService paymentService = new PaymentGateway();
        
        TransportBookingService svc = new TransportBookingService(
            distanceService,
            driverService,
            paymentService
        );
        
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));
        svc.book(req);
    }
}
