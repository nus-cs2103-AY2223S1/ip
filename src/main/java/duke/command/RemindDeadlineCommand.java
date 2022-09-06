package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TasksController;
import java.util.ArrayList;

public class RemindDeadlineCommand extends Command {
    /**
     * Execute RemindDeadlineCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {

        ArrayList<Task> deadlines = controller.getDeadlines();
        String response = "";
        if (deadlines.size() == 0) {
            response = "Congratulations! You have no deadlines ahead.";
        } else {
            response += "Here are all your upcoming deadlines:\n";
            response += controller.getTasksString(deadlines);
        }
        return response;
    }
}
