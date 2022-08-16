package duke;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String type) {
        super(String.format("The description of %s cannot be empty.", type));
    }
}
