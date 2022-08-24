package duke;

/**
 * Encapsulate a command that allows user to exit the program,
 * which is-a Command.
 */
public class ExitCommand extends Command {

    /**
     * Shows User interface for goodbye and set isExit to true.
     *
     * @param tasks list of existing tasks.
     * @param ui user interface to be shown.
     * @param storage to rewrite the data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.setIsExit(true);
        return ui.showBye();
    }
}
