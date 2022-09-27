package duke.exception;

/**
 * The DukeException regarding the executing of Duke Commands.
 */
public class DukeRuntimeException extends DukeException {
    /**
     * Constructs DukeRuntimeException with given information.
     * @param msg The given information.
     */
    public DukeRuntimeException(String msg) {
        super(" I'm sorry that an error occurs when executing your command, "
                + "please check with following information:\n" + msg);
    }
}
