package duke;

public class Parser {

    /**
     * Checks command and make sense of the command.
     *
     * @param command The command passed in by the user.
     * @return enum type of the command.
     */
    public static Command parse(String command) {
        if (command.startsWith("bye")) {
            return Command.QUIT;
        } else if (command.startsWith("list")) {
            return Command.LIST;
        } else if (command.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (command.startsWith("mark")) {
            return Command.MARK;
        } else if (command.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (command.startsWith("todo")) {
            return Command.TODO;
        } else if (command.startsWith("event")) {
            return Command.EVENT;
        } else if (command.startsWith("delete")) {
            return Command.DELETE;
        } else if (command.startsWith("find")) {
            return Command.FIND;
        } else {
            return Command.INVALID;
        }
    }
}
