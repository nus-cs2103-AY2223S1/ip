package duke.command;

import duke.task.TaskList;

public class HelpCommand implements Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays program usage instructions. Example: "
            + COMMAND_WORD;

    @Override
    public String execute(TaskList taskList) {
        String message = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s", ListCommand.MESSAGE_USAGE,
                MarkCommand.MESSAGE_USAGE, UnmarkCommand.MESSAGE_USAGE, TodoCommand.MESSAGE_USAGE,
                DeadlineCommand.MESSAGE_USAGE, EventCommand.MESSAGE_USAGE, DeleteCommand.MESSAGE_USAGE,
                FindCommand.MESSAGE_USAGE, HelpCommand.MESSAGE_USAGE);
        return message;
    }
}
