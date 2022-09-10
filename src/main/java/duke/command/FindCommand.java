package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The FindCommand class finds the list of matching tasks
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            TaskList matchingTasks = taskList.findTasks(keyword);
            return ui.printMatchedList(matchingTasks.toString());
        } catch (DukeException e) {
            return ui.printErr(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
