package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command to find a task.
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Creates a new FindCommand.
     *
     * @param toFind the string to search for in task descriptions within the task list.
     */
    public FindCommand(String toFind) {
        super();
        this.toFind = toFind;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.find(toFind);
        if (foundTasks.size() == 0) {
            throw new DukeException(String.format("No tasks found containing \"%s\"", toFind));
        }
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            listString.append(String.format("%d.%s\n", i + 1, foundTasks.get(i).toString()));
        }
        return listString.toString();
    }
}
