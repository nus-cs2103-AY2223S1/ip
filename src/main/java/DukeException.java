public class DukeException extends IllegalArgumentException {
    private final String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return Duke.formatText("☹ OOPS!!! " + this.message);
    }
}
