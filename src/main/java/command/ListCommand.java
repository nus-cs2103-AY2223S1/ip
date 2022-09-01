package command;

/**
 * Command that prints the existing task list
 * to the console.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    private static final String MESSAGE_SUCCESS = "HERE'S YOUR CURRENT LIST:\n %1$s";

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskList.toString()), taskList);
    }
}
