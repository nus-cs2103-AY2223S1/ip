package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * FindCommand finds any tasks that contain the search keyword.
 */
public class FindCommand extends Command {
    private String searchKeyword;

    /**
     * Constructor for FindCommand.
     * @param searchKeyword search keyword for tasks.
     */
    public FindCommand(String searchKeyword) {
        super();
        this.searchKeyword = searchKeyword;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindMessage(tasks.findTasks(searchKeyword));
    }
}
