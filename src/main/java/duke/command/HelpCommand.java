package duke.command;

import duke.task.TaskList;

/**
 * Represents a help command
 */
public class HelpCommand implements Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays program usage instructions. Example: "
            + COMMAND_WORD + " " + ListCommand.COMMAND_WORD + "\nCommands available: " + ByeCommand.COMMAND_WORD + " "
            + DeadlineCommand.COMMAND_WORD + " " + DeleteCommand.COMMAND_WORD + " " + EventCommand.COMMAND_WORD + " "
            + FindCommand.COMMAND_WORD + " " + HelpCommand.COMMAND_WORD + " " + ListCommand.COMMAND_WORD + " "
            + MarkCommand.COMMAND_WORD + " " + SortCommand.COMMAND_WORD + " " + TodoCommand.COMMAND_WORD + " "
            + UnmarkCommand.COMMAND_WORD;

    private String command;

    public HelpCommand() {
        this("");
    }

    /**
     * Constructor for a {@link HelpCommand}
     *
     * @param command Command that the user needs help with
     */
    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Executes a command
     *
     * @param taskList
     */
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
        case ByeCommand.COMMAND_WORD: {
            message = ByeCommand.MESSAGE_USAGE;
            break;
        }
        default:
            message = HelpCommand.MESSAGE_USAGE;
            break;
        }
        return message;
    }
}
