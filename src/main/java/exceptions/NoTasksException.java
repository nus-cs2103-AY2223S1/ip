package exceptions;

public class NoTasksException extends Exception {
    public NoTasksException() {
        super("You can't do that! There are no tasks added yet...");
    }
}
