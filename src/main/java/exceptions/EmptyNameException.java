package exceptions;

public class EmptyNameException extends Exception {
    public EmptyNameException() {
        super("Task Name cannot be empty");
    }
}
