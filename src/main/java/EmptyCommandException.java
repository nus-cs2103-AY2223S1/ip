public class EmptyCommandException extends DukeException{
    String type;

    EmptyCommandException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("OOPS!!! The description of a %s cannot be empty.", this.type);
    }
}
