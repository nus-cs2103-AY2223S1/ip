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
        int i = 0;
        String completedTasks = "";
        String uncompletedTasks = "";
        for (Task task : tasks.getTasks()) {
            i++;
            if (task.getStatus()) {
                completedIndex++;
                completedTasks = completedTasks + i + ". " + task + "\n";
            } else {
                uncompletedIndex++;
                uncompletedTasks = uncompletedTasks + i + ". " + task + "\n";
            }
        }
        return Ui.showPrintListMessage(uncompletedTasks, completedTasks, completedIndex, uncompletedIndex);
    }
}
