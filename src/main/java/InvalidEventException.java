public class InvalidEventException extends DukeException {
    private static String line = "\t____________________________________________________________";
    private static String message = "\t☹ OOPS!!! The description or date of an event cannot be empty.";
    public InvalidEventException() {
        super(line + "\n" + message + "\n" + line);
    }
    public InvalidEventException(String mess) {
        super(line + "\n" + "\t☹ OOPS!!! The " + mess + " of a event cannot be empty."+ "\n" + line);
    }
}
