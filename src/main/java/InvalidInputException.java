public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super("Error: " + message);
    }
}
