public class InvalidToDoException extends DukeException {
    private static String line = "\t____________________________________________________________";
    private static String message = "\t☹ OOPS!!! The description of a todo cannot be empty.";
    public InvalidToDoException() {
        super(line + "\n" + message + "\n" + line);
    }
}
