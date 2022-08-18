/**
 * Class DukeException an extension of the exception class to handle possible exceptions thrown by Duke.
 *
 * @author Yuvaraj Kumaresan
 */

public class DukeException extends Exception {

    /**
     * Constructor
     *
     * @param errorMessage The error message string.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Nested class DukeToDoException
     * <p>
     * DukeToDoException handles possible errors with the ToDo input phrase.
     */
    public static class DukeToDoException extends DukeException {

        /**
         * Constructor
         *
         * @param errorMessage The error message string.
         */
        public DukeToDoException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Nested class DukeCommandException
     * <p>
     * DukeCommandException handles possible incorrect command errors.
     */
    public static class DukeCommandException extends DukeException {

        /**
         * Constructor
         *
         * @param errorMessage The error message string.
         */
        public DukeCommandException(String errorMessage) {
            super(errorMessage);
        }
    }
}
