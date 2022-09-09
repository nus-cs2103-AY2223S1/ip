package duke.exceptions;

public class DukeInvalidDateException extends DukeException {

    public DukeInvalidDateException() {
        super("Please type your dates in the format yyyy-mm-dd");
    }

}
