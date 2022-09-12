package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List Command class.
 */
public class ListCommand extends Command {

    /**
     * Prints all the tasks in tasks.
     *
     * @param tasks The tasks to be executed.
     */
    @Override
    public String execute(TaskList tasks) {
        int completedIndex = 0;
        int uncompletedIndex = 0;
        String completedTasks = "";
        String uncompletedTasks = "";
        for (Task task : tasks.getTasks()) {
            if (task.getStatus()) {
                completedIndex++;
                completedTasks = completedTasks + completedIndex + ". " + task + "\n";
            } else {
                uncompletedIndex++;
                uncompletedTasks = uncompletedTasks + uncompletedIndex + ". " + task + "\n";
            }
        }
        return Ui.showPrintListMessage(uncompletedTasks, completedTasks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
