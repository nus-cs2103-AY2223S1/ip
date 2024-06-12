package duke;

/**
 * Handles possible exceptions thrown by duke.Duke.
 *
 * @author Yuvaraj Kumaresan
 */

public class DukeException extends Exception {

    /**
     * Constructor.
     *
     * @param errorMessage The error message string.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Handles possible errors with the duke.ToDo input phrase.
     */
    public static class DukeToDoException extends DukeException {

        /**
         * Constructor.
         *
         * @param errorMessage The error message string.
         */
        public DukeToDoException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Handles possible incorrect command errors.
     */
    public static class DukeCommandException extends DukeException {

        /**
         * Constructor.
         *
         * @param errorMessage The error message string.
         */
        public DukeCommandException(String errorMessage) {
            super(errorMessage);
        }
    }
}
