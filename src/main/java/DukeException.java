public class DukeException extends IllegalArgumentException {
    private final String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return Duke.formatText("☹ OOPS!!! " + this.message);
    }
}
