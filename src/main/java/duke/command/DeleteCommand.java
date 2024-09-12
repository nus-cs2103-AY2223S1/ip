package duke.command;

import duke.Storage;
import duke.StoreUndo;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * A command that is used to delete a Task.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class DeleteCommand extends Command {
    private int id;

    /**
     * Constructor for DeleteCommand.
     * @param id Index of the Task to be deleted.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * Deletes a Task from the list and returns the toString of the Task deleted.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return toString of the Task deleted.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        Task task = list.deleteTask(id);
        storage.writeToFile(list);
        if (task instanceof Deadline) {
            StoreUndo.updateUndo(new DeadlineCommand(task.getDescription(), ((Deadline) task).getBy()));
        } else if (task instanceof Event) {
            StoreUndo.updateUndo(new EventCommand(task.getDescription(), ((Event) task).getWhen()));
        } else {
            StoreUndo.updateUndo(new TodoCommand(task.getDescription()));
        }
        return Ui.deleteTask(task);
    }
}
