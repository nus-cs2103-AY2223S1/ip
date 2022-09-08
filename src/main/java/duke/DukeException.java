package duke;

public class DukeException extends Exception {

    /**
     * Handles Duke exceptions.
     * @param errorMessage Error Message to be called.
     */
    DukeException(String errorMessage) {
        super(errorMessage);
    }
}
