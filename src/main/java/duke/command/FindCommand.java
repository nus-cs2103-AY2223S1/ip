package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TasksController;

import java.util.ArrayList;

/**
 * FindCommand class will execute the find command.
 */
public class FindCommand extends Command {

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
        assert taskTime.length() == 0 : "The taskTime of should not be used for FindCommand";
        assert taskText.length() == 0 : "The taskText should not be used for FindCommand";
        assert taskIndex == -1 : "The taskIndex should not be used for CreateEventCommand";
        assert keywords != null : "The keywords should not be empty";
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
