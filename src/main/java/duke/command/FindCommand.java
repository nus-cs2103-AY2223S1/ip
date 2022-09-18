package duke.command;

import java.util.ArrayList;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Finds tasks by keyword search.
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Finds tasks based on keyword when command is called.
     *
     * @param toFind keyword to search for.
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(toFind);
        ui.listFoundTasks(foundTasks);
    }
}
