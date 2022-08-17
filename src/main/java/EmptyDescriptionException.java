public class EmptyDescriptionException extends DukeException {
    private final String type;
    public EmptyDescriptionException(String type) {
        super();
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", this.type);
    }
}
