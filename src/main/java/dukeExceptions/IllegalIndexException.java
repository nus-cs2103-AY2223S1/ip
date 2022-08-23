package dukeExceptions;

public class IllegalIndexException extends DukeException {
    public IllegalIndexException() {
        super("The index provided is illegal, or not an int.");
    }
}
