package duke;

/**
 * Encapsulate a command that allows user to exit the program,
 * which is-a Command.
 *
 * @author: Jonas Png
 */
public class ExitCommand extends Command {

    /**
     * Shows User interface for goodbye and set isExit to true.
     *
     * @param tasks list of existing tasks
     * @param ui user interface to be shown
     * @param storage to rewrite the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        this.setIsExit(true);
    }
}
