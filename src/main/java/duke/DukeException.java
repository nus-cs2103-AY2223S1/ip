package duke;

public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        super(message);
    }


    /**
     * Returns the message of the exception.
     * @return Message of the exception.
     */
    public String toString() {
        return message;
    }
}
