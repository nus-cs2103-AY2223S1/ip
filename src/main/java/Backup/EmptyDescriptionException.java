public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}