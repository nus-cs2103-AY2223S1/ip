public class DukeException extends Exception {
    String message;

    DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Oops! " + message;
    }
}
