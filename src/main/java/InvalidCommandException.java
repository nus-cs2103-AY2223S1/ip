public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.getMessage() + " is not a valid command.\nPlease " +
                "use the commands todo, deadline or event to add a task!";
    }
}
