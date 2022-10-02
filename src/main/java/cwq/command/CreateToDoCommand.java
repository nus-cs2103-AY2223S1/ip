package cwq.command;

import cwq.storage.Storage;
import cwq.task.ToDo;
import cwq.task.TasksController;
/**
 * CreatToDoCommand will execute the command of creating a new todo.
 */
public class CreateToDoCommand extends Command {

    /**
     * Execute CreateToDoCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        assert taskTime.length() == 0 : "The taskTime should not be used for CreateToDoCommand";
        assert taskText.length() > 0 : "Content of an event should not be empty";
        assert taskIndex == -1 : "The taskIndex should not be used for CreateToDoCommand";
        assert keywords[0].equals("") : "The keywords should not be used for CreateToDoCommand";
        String response = "";
        ToDo todo = new ToDo(taskText);
        controller.addToList(todo);
        response += "Successfully added! You can see it in your task list as follows:\n";
        response += todo.toString();
        return response;
    }
}
