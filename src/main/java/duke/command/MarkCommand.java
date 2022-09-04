package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that marks a task in the list as done.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
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
     * Executes the command by marking the task.
     *
     * @param taskList List of tasks being operated on.
     * @param ui UI that prints corresponding responses.
     * @param storage Storage for saving purposes if applicable.
     * @throws DukeException If the input index is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = taskList.getTaskArrayList();
        boolean isValidIndex = (index >= 1) && (index <= list.size());

        if (!isValidIndex) {
            String errorMessage = "OOPS!!! The index of the task to be marked/unmarked/deleted must be within range.";
            throw new DukeException(errorMessage);
        }

        Task task = list.get(index - 1);
        taskList.mark(this.index);
        return ui.showMark(task);
    }

}
