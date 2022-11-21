package duke.command;

import java.util.ArrayList;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Checker;
import duke.task.Task;

/**
 * A class that handles the find command.
 */
public class FindCommand extends Command {

    private String string;

    /**
     * Constructs the find command.
     *
     * @param string a string to dictate what to find.
     */
    public FindCommand(String string) {
        this.string = string;
    }

    /**
     * Finds the required tasks from the task list.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws DukeException if cannot be found.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> arr = new ArrayList<>();
        for (Task task: tasks.getArr()) {
            if (Checker.contains(task, this.string)) {
                arr.add(task);
            }
        }
        ui.sayMatching(arr);
    }

    /**
     * Ensures that the program does not exit.
     *
     * @return boolean indicating not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
