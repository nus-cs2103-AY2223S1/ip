public class DukeException extends Exception {
    private static final String oopsMessage = "☹ OOPS!!!";

    public DukeException(String errorMessage) {
        super(String.format("%s %s", oopsMessage, errorMessage));
    }
}
