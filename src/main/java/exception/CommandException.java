package exception;

public class CommandException extends Exception {
    public CommandException() {
        super("☹ OOPS!!! I'm Sorry, Yem doesn't know what that means.");
    }

    public CommandException(String error) {
        super("☹ OOPS!!! " + error);
    }
}
