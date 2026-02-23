public class StudentDiscountPolicy implements DiscountPolicy {
    private static final double THRESHOLD  = 180.0;
    private static final double DISCOUNT   = 10.0;

    @Override
    public double discountAmount(double subtotal, int lineCount) {
        return subtotal >= THRESHOLD ? DISCOUNT : 0.0;
    }
}
