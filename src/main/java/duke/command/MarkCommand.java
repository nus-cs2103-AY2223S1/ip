package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A command that is used to mark a Task as done.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class MarkCommand extends Command {
    private int id;

    /**
     * Constructor for MarkCommand.
     * @param id Index of the Task to be marked as done.
     */
    public MarkCommand(int id) {
        this.id = id;
    }

    /**
     * Marks the Task at index as done and returns the String to be shown to the user.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return String to be shown to the user.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        Task task = list.markTask(id);
        storage.writeToFile(list);
        return Ui.markTask(task);
    }
}
