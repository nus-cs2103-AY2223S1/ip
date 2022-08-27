package ip.exception;

public class InvalidCommand extends DukeException {
    private String commandGiven;

    public InvalidCommand(String s) {
        commandGiven = s;
    }

    @Override
    public String toString() {
        return commandGiven + " is an invalid command.\n" +
                "List of valid commands:\n" +
                "list\ntodo\ndeadline\nevent\nmark\nunmark\ndelete\nbye";
    }
}
