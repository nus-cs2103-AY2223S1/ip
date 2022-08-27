package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that deletes a task from the list.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
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
     * Executes the command by deleting the task from the list.
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
            taskList.delete(this.index);
            ui.showDelete(t, list.size());
        } else {
            String s = "☹ OOPS!!! The index of the task to be marked/unmarked/deleted must be valid/within range.";
            throw new DukeException(s);
        }
    }
}
