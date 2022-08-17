public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.toString() + "I'm sorry, but I don't know what that means :-(";
    }
}
