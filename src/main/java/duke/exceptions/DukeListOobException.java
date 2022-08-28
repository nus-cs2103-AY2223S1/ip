package duke.exceptions;

public class DukeListOobException extends DukeException {
    private static final String MESSAGE = "The list does not contain an entry of index %d.\n";

    public DukeListOobException(int index) {
        super(String.format(MESSAGE, index));
    }
}
