package duke.exceptions;

public class NoTimeException extends DukeException {
    private String type;

    public NoTimeException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("The time of a %s cannot be empty.", this.type);
    }
}
