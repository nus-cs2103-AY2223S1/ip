package duke;


/**
 * DukeException class that allows the
 * program throw exceptions specific to Duke
 *
 * @author Gerald Teo Jin Wei
 * @version 0.1
 * @since 2022-08-28
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException for exceptions specific to Duke
     * @param message Error message of the DukeException
     */
    public DukeException(String message) {
        super(message);
    }

}
