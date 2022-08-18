public class DorisException extends Exception {
    private final String errorMessage;

    public DorisException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
