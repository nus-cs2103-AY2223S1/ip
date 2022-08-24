package command;

/**
 * Command that prints the existing task list
 * to the console.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute() {
        return new CommandResult(taskList.toString());
    }
}
