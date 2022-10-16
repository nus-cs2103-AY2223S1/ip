package duke.commands;

import duke.task.Task;
import duke.TaskList;
import duke.Storage;

import java.util.List;

/**
 * A <code>Command</code> class that handles the listing of all <code>Task</code>
 */
public class ListCommand extends Command {

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Storage storage, TaskList tl) {
        List<Task> listOfTasks = tl.getTasks();
        String response;
        if (listOfTasks.isEmpty()) {
            response = "Your list is empty! Why not add a task first?";
        } else {
            response = "Here are the tasks in your list:\n";
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task curTask = listOfTasks.get(i);
                response = response.concat((i + 1) + "." + curTask.toString() + "\n");
            }
        }
        return response;
    }
}
