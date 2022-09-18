package duke.exceptions;

public class DukeBadFormatException extends DukeException {

    public DukeBadFormatException(String expectedFormat) {
        super(String.format("Expected Format: %s", expectedFormat));
    }
}
