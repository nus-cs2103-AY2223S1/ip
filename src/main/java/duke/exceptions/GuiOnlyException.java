package duke.exceptions;

/**
 * Exception thrown when the feature is not available on the CLI side of the app.
 */
public class GuiOnlyException extends DukeException {
    private static final String DESCRIPTION = "This feature is only useable on the GUI version of duke!";

    public GuiOnlyException() {
        super(DESCRIPTION);
    }
}
