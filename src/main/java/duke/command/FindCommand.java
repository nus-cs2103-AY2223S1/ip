package duke.command;

import java.util.List;

import duke.Response;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;


/**
 * FindCommand represents a command to find all tasks that match a keyword.
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    /**
     * Creates a FindCommand to find matching tasks.
     *
     * @param taskList The TaskList to search in.
     * @param inputArr The input String array.
     */
    public FindCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    /**
     * Finds all the tasks that match the keyword.
     *
     * @return String representing the list of matching tasks.
     * @throws DukeException If the input array is invalid.
     */
    @Override
    public Response action() throws DukeException {
        if (this.inputArr.length < 2) {
            throw new DukeException("Missing keyword.");
        }
        List<Task> tasks = this.taskList.findTasks(this.inputArr[1]);
        int size = tasks.size();
        if (size == 0) {
            return new Response("There are no matching tasks in your list.\n");
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= size; i++) {
                sb.append(String.format("%d.%s\n", i, tasks.get(i - 1)));
            }
            return new Response(sb.toString());
        }
    }
}
