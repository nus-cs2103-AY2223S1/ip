package cwq.command;

import cwq.storage.Storage;
import cwq.task.Deadline;
import cwq.task.TasksController;

/**
 * CreatDeadlineCommand will execute the command of creating a new deadline.
 */
public class CreateDeadlineCommand extends Command {

    /**
     * Execute CreateDeadlineCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        assert taskTime.length() > 0 : "Time of a deadline should not be empty";
        assert taskText.length() > 0 : "Content of a deadline should not be empty";
        assert taskIndex == -1 : "The taskIndex should not be used for CreateDeadlineCommand";
        assert keywords[0].equals("") : "The keywords should not be used for CreateDeadlineCommand";
        String response = "";
        Deadline ddlTask = new Deadline(taskText, taskTime);
        controller.addToList(ddlTask);
        response += "Successfully added! You can see it in your task list as follows:\n";
        response += ddlTask.toString();
        return response;
    }
}
