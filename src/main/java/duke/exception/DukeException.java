package duke.exception;

public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DukeException) {
            DukeException e = (DukeException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
