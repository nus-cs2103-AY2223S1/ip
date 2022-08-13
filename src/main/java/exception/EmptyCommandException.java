package exception;

public class EmptyCommandException extends CommandException {
    public EmptyCommandException() {
        super("The command cannot be empty");
    }
}
