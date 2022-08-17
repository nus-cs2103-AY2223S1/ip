package command;

public class CommandException extends Exception {
    CommandException(String errorMsg) {
        super(errorMsg);
    }
}
