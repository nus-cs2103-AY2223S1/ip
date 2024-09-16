package duke.exceptions;

public class DukeInvalidReadException extends DukeException{

    public DukeInvalidReadException() {
        super("There is issue in reading the stored task");
    }
}
