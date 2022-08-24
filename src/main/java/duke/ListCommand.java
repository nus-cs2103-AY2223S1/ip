package duke;

/**
 * Encapsulate a command that allows user see all existing items in TaskList,
 * which is-a Command.
 */
public class ListCommand extends Command {

    /**
     * Show list of existing tasks.
     *
     * @param tasks list of existing tasks.
     * @param ui user interface to be shown.
     * @param storage to rewrite the data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
