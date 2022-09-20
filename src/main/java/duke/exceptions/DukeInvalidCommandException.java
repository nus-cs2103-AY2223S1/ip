package duke.exceptions;

public class DukeInvalidCommandException extends DukeException {

    public DukeInvalidCommandException() {
        super("You have entered an invalid command");
    }
}
