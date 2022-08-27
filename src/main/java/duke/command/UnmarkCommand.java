package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that unmarks a task in the list as done.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Instantiates the unmark command to be executed
     *
     * @param index Index of the task in the list to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns whether command is an ExitCommand.
     *
     * @return Whether the command will cause the Duke program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by unmarking the task.
     *
     * @param taskList List of tasks being operated on.
     * @param ui UI that prints corresponding responses.
     * @param storage Storage for saving purposes if applicable.
     * @throws DukeException If the input index is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = taskList.getTaskArrayList();
        if ((index > 0) && index < list.size() && (list.get(index - 1) != null)) {
            Task t = list.get(index - 1);
            taskList.unmark(this.index);
            ui.showUnmark(t);
        } else {
            String s = "☹ OOPS!!! The index of the task to be marked/unmarked/deleted must be valid/within range.";
            throw new DukeException(s);
        }
    }

}
