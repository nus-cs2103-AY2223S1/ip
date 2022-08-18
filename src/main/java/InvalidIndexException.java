public class InvalidIndexException extends DukeException {
    private static String line = "\t____________________________________________________________";
    private static String message = "\t☹ OOPS!!! This item doesn't exist :-(";
    public InvalidIndexException() {
        super(line + "\n" + message + "\n" + line);
    }
}