package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command child class that finds tasks with specified string.
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Initialises FindCommand object with specified search query.
     *
     * @param toFind String specifying search query
     */
    public FindCommand(String toFind) {
        super();
        this.toFind = toFind;
    }

    /**
     * Finds and prints found tasks to screen based on stored search query.
     *
     * @param tasks   TaskList object corresponding to all tasks
     * @param ui      Ui object to show user output/errors
     * @param storage Storage object to save data after execution
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(toFind);
        ui.showMessage("Here are the tasks found: ");
        ui.showList(foundTasks);
    }
}
