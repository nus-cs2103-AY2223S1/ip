public class DukeException extends RuntimeException {

    public static final String OOPS = " ☹ OOPS!!! ";

    public DukeException(String message) {
        super(OOPS + message);
    }
}
