package tako.command;

import tako.Storage;
import tako.TaskList;
import tako.Ui;

/**
 * Command to find tasks in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand to find using a keyword.
     *
     * @param keyword Keyword to find tasks with.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find tasks containing the keyword in their description.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Task storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFind(tasks.find(keyword));
    }

    /**
     * Returns false as Tako cannot exit after this command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
