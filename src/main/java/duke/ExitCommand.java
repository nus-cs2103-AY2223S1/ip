package duke;

/**
 * Represents an Exit Command, which quits Duke's service to user.
 *
 * @author Elgin
 */
public class ExitCommand extends Command {
    /**
     * Executes the Exit command, and stops Duke and user interaction, ending off with a bye.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.isExit = true;

        ui.sayBye();
    }
}
