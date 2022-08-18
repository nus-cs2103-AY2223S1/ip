public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "Oops! " + message;
    }
}
