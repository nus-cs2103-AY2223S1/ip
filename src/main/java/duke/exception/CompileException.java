package duke.exception;

/**
 * The DukeException regarding user inputs.
 */
public class CompileException extends DukeException {
    /**
     * Constructs Compile Exception with given information.
     * @param msg The given information.
     */
    public CompileException(String msg) {
        super(" I'm sorry that your entry does not compile, "
                + "please check with following information:\n"
                + msg);
    }
}
