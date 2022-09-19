package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** Command that counts completed and uncompleted tasks */
public class CountCommand extends Command {

    private boolean isCountCompletedTasks;

    public CountCommand(boolean isCountCompletedTasks) {
        this.isCountCompletedTasks = isCountCompletedTasks;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (isCountCompletedTasks) {
            int numOfTasks = taskList.countTasks(true);
            return ui.showNumberOfTasks(numOfTasks, "completed");
        } else {
            int numOfTasks = taskList.countTasks(false);
            return ui.showNumberOfTasks(numOfTasks, "uncompleted");
        }
    }
}
