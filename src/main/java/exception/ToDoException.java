package exception;

public class ToDoException extends CommandException  {
    public ToDoException() {
        super("The description of a todo cannot be empty.");
    }
}
