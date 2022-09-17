package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the list command to list all the user's tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints out list of tasks individually with index.
     *
     * @param tasks List of tasks.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<String> responseLines = new ArrayList<>();
        responseLines.add("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.taskCount(); i++) {
            Task task = tasks.getTask(i);
            responseLines.add(i + ". " + task);
        }
        return String.join("\n", responseLines);
    }
}
