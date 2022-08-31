package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Command for listing all the tasks in the task list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Return a string containing the list of tasks.
     * @param task The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The list of tasks.
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        ArrayList<Task> listOfTasks = task.getListOfTasks();
        String stringListOfTasks = "Here are the tasks in your list\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            stringListOfTasks += (i + 1) + ". " + listOfTasks.get(i).taskInfo() + "\n";
        }
        return stringListOfTasks;
    }

}
