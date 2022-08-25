import java.io.IOException;

public class Parser {

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
