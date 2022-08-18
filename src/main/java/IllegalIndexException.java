public class IllegalIndexException extends Exception {
    public IllegalIndexException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
