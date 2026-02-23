public class SendResult {
    public final boolean isError;
    public final String  errorMessage;

    private SendResult(boolean isError, String errorMessage) {
        this.isError      = isError;
        this.errorMessage = errorMessage;
    }

    public static SendResult ok() {
        return new SendResult(false, null);
    }

    public static SendResult error(String message) {
        return new SendResult(true, message);
    }
}
