package duke.exception;

/**
 * DukeException class for exceptions specific for Duke
 */
public class DukeException extends Exception{
        /**
         * Constructor for the DukeException class.
         * @param message Input exception message.
         */
        public DukeException(String message) {
            super(message);
        }
}
