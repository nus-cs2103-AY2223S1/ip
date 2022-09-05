package duke.command;

public class CommandException extends Exception {
    public CommandException(String errorMessage) {
        super(errorMessage);
    }
}
