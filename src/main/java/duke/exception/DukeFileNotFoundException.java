package duke.exception;

public class DukeFileNotFoundException extends DukeException {
    public DukeFileNotFoundException() {
        super("File not found, please try again");
    }
}
