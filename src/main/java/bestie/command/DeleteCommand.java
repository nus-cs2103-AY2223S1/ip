package bestie.command;

import bestie.tasks.Task;
import bestie.tasks.ToDoList;
import bestie.utils.Ui;

/**
 * DeleteCommand class representing a delete command executed by the user.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;
    private ToDoList list;

    /**
     * Constructs delete command.
     */
    public DeleteCommand(int indexToDelete, ToDoList list) {
        super("delete");
        this.indexToDelete = indexToDelete;
        this.list = list;
    }

    /**
     * Executes the delete command by deleting the item from the list and giving the user
     *      information about the deadline added.
     *
     * @return String representing the information about the deleted event.
     */
    @Override
    public String execute() {
        Task toBeDeleted = list.getTask(indexToDelete - 1);
        list.deleteTask(indexToDelete - 1);
        return Ui.taskDeletedMessage(toBeDeleted, list);
    }
}
