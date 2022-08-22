package sky;

import sky.command.*;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {
    public static Command parse(String fullCommand) {
        if (fullCommand.startsWith("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            return new MarkCommand(fullCommand);
        } else if (fullCommand.startsWith("unmark")) {
            return new UnmarkCommand(fullCommand);
        } else if (fullCommand.startsWith("todo")) {
            return new TodoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return new DeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return new EventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else {
            return new InvalidCommand();
        }
    }
}
