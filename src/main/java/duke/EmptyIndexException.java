package duke;

public class EmptyIndexException extends DukeException {
    public EmptyIndexException(String type) {
        super(String.format("The index to %s cannot be empty.", type));
    }
}
