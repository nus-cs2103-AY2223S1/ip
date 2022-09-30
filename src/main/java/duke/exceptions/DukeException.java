package duke.exceptions;

/**
 * The DukeException wraps up all checked exceptions that occur in the Duke program.
 * This provides a more systematic way to handle exceptions.
 */
public abstract class DukeException extends Exception {

    private String guiMessage;

    /**
     * Constructs a DukeException exception.
     *
     * @param message The exception message.
     */
    public DukeException(String message, String guiMessage) {
        super(message);
        this.guiMessage = guiMessage;
    }

    /**
     * Gets the guiMessage stored in this exception.
     *
     * @return The guiMessage stored in this exception.
     */
    public String getGuiMessage() {
        return guiMessage;
    }
}
