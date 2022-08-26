public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
