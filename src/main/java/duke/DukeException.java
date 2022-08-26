package duke;

public class DukeException extends Exception {

    /**
     * Handles Duke exceptions.
     * @param errorMessage
     */
    DukeException(String errorMessage) {
        super(errorMessage);
    }
}
