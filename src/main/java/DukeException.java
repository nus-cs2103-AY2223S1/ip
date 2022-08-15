public class DukeException extends IllegalArgumentException {
    protected String message;
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Sorry I don't know what you mean. Please follow the format!";
    }
}
