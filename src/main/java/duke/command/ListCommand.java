package duke.command;

/**
 * Command for listing Tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommad.
     */
    public ListCommand() {
        super.isExit = false;
    }

    /**
     * Displays Tasks in TaskList.
     */
    @Override
    public void execute() {
        Command.ui.formatAndPrint(Command.taskList.getLogs());
    }
}
