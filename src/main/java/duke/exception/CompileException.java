package duke.exception;

/**
 * The DukeException regarding user inputs.
 */
public class CompileException extends DukeException {
    /**
     * The constructor of Compile Exception with given information.
     * @param msg The given information.
     */
    public CompileException(String msg) {
        super(" I'm sorry that your entry does not compile, "
                + "please check with following information:\n"
                + msg);
    }

    /**
     * Return boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CompileException) {
            CompileException compileException = (CompileException) obj;
            if (compileException == null) {
                return false;
            }
            return this.getMessage().equals(compileException.getMessage());
        }
        return false;
    }
}
