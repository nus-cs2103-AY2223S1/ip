public class InvalidDeadlineException extends DukeException {
    private static String line = "\t____________________________________________________________";
    private static String message = "\t☹ OOPS!!! The description or date of a deadline cannot be empty.";
    public InvalidDeadlineException() {
        super(line + "\n" + message + "\n" + line);
    }
    public InvalidDeadlineException(String mess) {
        super(line + "\n" + "\t☹ OOPS!!! The " + mess + " of a deadline cannot be empty."+ "\n" + line);
    }
}
