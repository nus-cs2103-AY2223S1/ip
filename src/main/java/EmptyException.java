public abstract class EmptyException extends DukeException{
    public EmptyException(String command) {
        super(String.format("The description of a %s cannot be empty.", command));
    }
}
