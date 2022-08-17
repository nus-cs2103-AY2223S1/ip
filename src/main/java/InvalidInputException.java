public class InvalidInputException extends DukeException {
    private static String line = "\t____________________________________________________________";
    private static String message = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public InvalidInputException() {
        super(line + "\n" + message + "\n" + line);
    }
}
