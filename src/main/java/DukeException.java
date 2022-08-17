public class DukeException extends Exception {
    DukeException (String s) {
        super(s);
    }

    public DukeException(String s, Throwable cause) {
        super(s, cause);
    }
}
