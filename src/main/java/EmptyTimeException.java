public class EmptyTimeException extends DukeException {
    private final String type;

    public EmptyTimeException(String type) {
        super();
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! The date/time of a %s cannot be empty.", this.type);
    }
}
