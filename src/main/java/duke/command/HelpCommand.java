package duke.command;

import duke.task.TaskList;

public class HelpCommand implements Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays program usage instructions. Example: "
            + COMMAND_WORD + " " + ListCommand.COMMAND_WORD;

    private String command;

    public HelpCommand() {
        this("");
    }

    public HelpCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList taskList) {
        String message;
        switch (this.command) {
        case ListCommand.COMMAND_WORD: {
            message = ListCommand.MESSAGE_USAGE;
            break;
        }
        case MarkCommand.COMMAND_WORD: {
            message = MarkCommand.MESSAGE_USAGE;
            break;
        }
        case UnmarkCommand.COMMAND_WORD: {
            message = UnmarkCommand.MESSAGE_USAGE;
            break;
        }
        case TodoCommand.COMMAND_WORD: {
            message = TodoCommand.MESSAGE_USAGE;
            break;
        }
        case DeadlineCommand.COMMAND_WORD: {
            message = DeadlineCommand.MESSAGE_USAGE;
            break;
        }
        case EventCommand.COMMAND_WORD: {
            message = EventCommand.MESSAGE_USAGE;
            break;
        }
        case DeleteCommand.COMMAND_WORD: {
            message = DeleteCommand.MESSAGE_USAGE;
            break;
        }
        case FindCommand.COMMAND_WORD: {
            message = FindCommand.MESSAGE_USAGE;
            break;
        }
        default:
            message = HelpCommand.MESSAGE_USAGE;
            break;
        }
        return message;
    }
}
