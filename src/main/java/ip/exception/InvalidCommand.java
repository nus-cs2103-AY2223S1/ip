package ip.exception;

/**
 * Exception thrown when command entered is invalid.
 */
public class InvalidCommand extends DukeException {
    private String commandGiven;

    public InvalidCommand() {
        commandGiven = "";
    }

    public InvalidCommand(String s) {
        commandGiven = s;
    }

    @Override
    public String toString() {
        if (commandGiven.isEmpty()) {
            return "No command given!";
        }
        return "\"" + commandGiven + "\"" + " is an invalid command.\n"
               + "Valid commands:\n"
               + "list, todo, deadline, event, find, mark, unmark, delete, undo, bye";
    }
}
