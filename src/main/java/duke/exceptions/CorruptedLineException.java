package duke.exceptions;

public class CorruptedLineException extends DukeException {
    private static final String DESCRIPTION = "There was an error parsing this line";

    public CorruptedLineException() {
        super(DESCRIPTION);
    }
}
