package duke.exception;

public class DescriptionNotSpecifyException extends DukeException {
    public DescriptionNotSpecifyException(String command) {
        super("We expect some description to be specified after " + command + " command!\nIt should not be empty!");
    }
}
