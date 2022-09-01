package duke.commands;

import duke.Response;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;

/**
 * Displays the user's current tasks in a numbered list format.
 */
public class ListTasksCommand extends Command {
    @Override
    public Response execute(TaskList taskList, Storage storage) {
        List<Task> tasks = taskList.getAll();
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            message.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return new Response(message.toString());
    }
}