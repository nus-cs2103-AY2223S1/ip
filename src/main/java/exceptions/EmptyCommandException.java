package exceptions;

public class EmptyCommandException extends DukeException{
    String type;

    public EmptyCommandException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("OOPS!!! The description of a %s cannot be empty.", this.type);
    }
}
