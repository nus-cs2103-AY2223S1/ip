package duke.exceptions;

public class EmptyCommandException extends DukeException {
    private String type;

    public EmptyCommandException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("The description of a %s cannot be empty.", this.type);
    }
}
