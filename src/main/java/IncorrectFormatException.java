public class IncorrectFormatException extends RuntimeException {
    public IncorrectFormatException (String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
