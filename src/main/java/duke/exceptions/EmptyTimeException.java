package duke.exceptions;

public class EmptyTimeException extends DukeException {
    private static final String DESCRIPTION = "%s requires a time/duration information, use <description> %s <duration> to indicate";

    public EmptyTimeException(String commandName, String seperator) {
        super(String.format(DESCRIPTION, commandName, seperator));
    }
}
