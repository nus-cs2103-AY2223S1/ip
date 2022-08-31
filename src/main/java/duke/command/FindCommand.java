package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.response.Response;
import duke.task.Task;
import duke.task.TasksList;


/**
 * Represents a command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    private static final String FOUND_MSG = "Here are the matching tasks in your list:\n\n";
    private static final String NOT_FOUND_MSG = "There are no matching tasks in your list.\n";
    private String[] inputArray;
    private TasksList tasksList;

    /**
     * Creates a new FindCommand instance.
     * @param tasksList The TasksList to search in.
     * @param inputArray The array that represents the user input.
     */
    public FindCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Finds the tasks that match the keywords.
     * @return The list of matching Tasks represented in a single Response.
     * @throws DukeException If the input array is invalid.
     */
    @Override
    public Response execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing keyword!");
        }

        String keyword = this.inputArray[1];
        List<Task> matchingTasks = this.tasksList.findMatchingTasks(keyword.split(" "));
        int size = matchingTasks.size();

        if (size == 0) {
            return new Response(FindCommand.NOT_FOUND_MSG);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(FindCommand.FOUND_MSG);
            for (int i = 1; i <= size; i++) {
                sb.append(String.format("%d. %s\n", i, matchingTasks.get(i - 1)));
            }
            return new Response(sb.toString());
        }
    }
}
