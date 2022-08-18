public class InvalidArgumentException extends DukeException {

    private Commands command;

    public InvalidArgumentException(Commands commands) {
        super(commands.name());
        this.command = commands;
    }

    @Override
    public String toString() {
        String message = "";
        switch (this.command) {
            case DEADLINE:
                message += "\nPlease add a /by to declare the time the deadline is meant to be set.";
                break;
            case EVENT:
                message += "\nPlease add a/at to declare the time the event is at.";
                break;
            case MARK:
            case UNMARK:
            case DELETE:
                message += "\nPlease input a integer within the range of the tasks.";
                break;
        }
        return "OOPS!!! The description of the " +  this.command + " command is invalid!" + message;
    }
}
