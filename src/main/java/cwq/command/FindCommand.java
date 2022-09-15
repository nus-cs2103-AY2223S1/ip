package cwq.command;

import cwq.storage.Storage;
import cwq.task.Task;
import cwq.task.TasksController;

import java.util.ArrayList;

/**
 * FindCommand class will execute the find command.
 */
public class FindCommand extends Command {

    /**
     * Execute FindCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        assert taskTime.length() == 0 : "The taskTime of should not be used for FindCommand";
        assert taskText.length() == 0 : "The taskText should not be used for FindCommand";
        assert taskIndex == -1 : "The taskIndex should not be used for CreateEventCommand";
        assert !keywords[0].equals("") : "The keywords should not be empty";
        ArrayList<Task> result = controller.findByKeyword(keywords);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < result.size(); ++i) {
            s.append(i + 1).append(". ").append(result.get(i).toString()).append("\n");
        }
        String response = "Here are what we found for you: \n";
        response += s.toString();
        return response;
    }
}
