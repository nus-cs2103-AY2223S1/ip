package duke.exception;

public class DukeRuntimeException extends DukeException {
    public DukeRuntimeException(String msg) {
        super(" I'm sorry that an error occurs when executing your command, "
                + "please check with following information:\n" + msg);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DukeRuntimeException) {
            DukeRuntimeException e = (DukeRuntimeException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
