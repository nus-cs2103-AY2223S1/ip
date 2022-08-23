package duke.exception;

public class CompileException extends DukeException {
    public CompileException(String msg) {
        super(" I'm sorry that your entry does not compile, "
                + "please check with following information:\n"
                + msg);
    }
}
