package duke;

/**
 * RuntimeException specifically for the Duke program.
 *
 * @author Aaron Pang
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeException extends RuntimeException {
    private String message;

    /**
     * Constucts the Duke exception.
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the message of the exception.
     * @return Message of the exception.
     */
    public String toString() {
        return message;
    }
}
