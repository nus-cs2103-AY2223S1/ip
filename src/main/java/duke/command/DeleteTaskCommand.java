package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;
import duke.exception.NoSuchTaskException;
/**
 * DeleteCommand will execute the command of deleting a task.
 */
public class DeleteTaskCommand extends Command {

    /**
     * An abstract method that every child class needs to implement
     * @param controller Duke task controller
     * @param taskText if it's add task command, then pass the context of the task.
     * @param taskTime if it's add Event or Deadline, then pass the time
     * @param taskIndex if it's mark or unmark command, then pass the task number
     * @param keywords if it's find command, then pass the keywords
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        String response = "";
        try {
            controller.deleteFromList(taskIndex);
            response = "Successfully deleted! You can use list command to check your tasks.\n";
        } catch (NoSuchTaskException nte) {
            response = "Your target task doesn't exist. Please try again...";
        }
        return response;
    }

}
