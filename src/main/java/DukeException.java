public class DukeException extends Exception{
    private static final String OOPS_MESSAGE = "☹ OOPS!!!";

    DukeException(String message) {
        super(String.format("%s %s", DukeException.OOPS_MESSAGE, message));
    }
}
