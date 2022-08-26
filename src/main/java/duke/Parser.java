package duke;

import duke.command.*;

public class Parser {

    /**
     * Determines whichever type of command object should be created
     * based on the string provided.
     * @param fullCommand The string provided by user.
     * @return The corresponding command to be carried out.
     */
    public static Command parseCommand(String fullCommand) {
        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.contains("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.contains("unmark")) {
            return new UnmarkCommand(fullCommand);
        } else if (fullCommand.contains("mark")) {
            return new MarkCommand(fullCommand);
        } else if (fullCommand.contains("todo")) {
            return new ToDoCommand(fullCommand);
        } else if (fullCommand.contains("deadline")) {
            return new DeadlineCommand(fullCommand);
        } else if (fullCommand.contains("event")) {
            return new EventCommand(fullCommand);
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            return new UnknownCommand();
        }
    }
}
