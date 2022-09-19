package duke;

/**
 * DukeException contains all exceptions related to the Duke program.
 */
class DukeException extends Exception {
    /**
     * Constructs a DukeException.
     *
     * @param errorMessage a relevant error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
