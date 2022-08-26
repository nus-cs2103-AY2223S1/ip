package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * FIndCommand class
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks that contain keyword
     *
     * @param storage Disk storage
     * @param tasks   Task list
     * @param ui      Ui to display to users
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.findTasks(keyword);
    }
}
