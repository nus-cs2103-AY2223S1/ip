package duke.exception;

public class CompileException extends DukeException {
    public CompileException(String msg) {
        super(" I'm sorry that your entry does not compile, "
                + "please check with following information:\n"
                + msg);
    }

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
