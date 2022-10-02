package cwq.command;

import cwq.storage.Storage;
import cwq.task.Event;
import cwq.task.TasksController;
/**
 * CreatEventCommand will execute the command of creating a new event.
 */
public class CreateEventCommand extends Command {

    /**
     * Execute CreateEventCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        assert taskTime.length() > 0 : "Time of an event should not be empty";
        assert taskText.length() > 0 : "Content of an event should not be empty";
        assert taskIndex == -1 : "The taskIndex should not be used for CreateEventCommand";
        assert keywords[0].equals("") : "The keywords should not be used for CreateEventCommand";
        String response = "";
        Event event = new Event(taskText, taskTime);
        controller.addToList(event);
        response += "Successfully added! You can see it in your task list as follows:\n";
        response += event.toString();
        return response;

    }
}
