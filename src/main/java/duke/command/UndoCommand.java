package duke.command;

import java.util.ArrayList;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class UndoCommand extends Command {

    private static ArrayList<ArrayList<Task>> history = new ArrayList<>();

    protected static void addHistory(TaskList tasks) {
        ArrayList<Task> clonedArray = (ArrayList<Task>) tasks.getArr().clone();
        history.add(clonedArray);
    }


    /**
     * Executes some command.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     * @throws DukeException if command cannot be executed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<Task> arr = history.remove(history.size() - 1);
            tasks.overwrite(arr);
            storage.overwrite();
            ui.sayUndo();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No more previous history!!!");
        }
    }

    /**
     * Makes sure program continues and not exit.
     * @return boolean indicating not exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes the last element of the history.
     */
    public static void deleteLast() {
        history.remove(history.size() - 1);
    }
}
