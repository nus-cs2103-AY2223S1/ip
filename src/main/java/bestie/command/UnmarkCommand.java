package bestie.command;

import bestie.tasks.ToDoList;
import bestie.utils.Ui;

/**
 * Unmark Command class representing the unmark command executed by the user.
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;
    private ToDoList list;

    /**
     * Constructs unmark command.
     */
    public UnmarkCommand(int indexToUnmark, ToDoList list) {
        super("unmark");
        this.indexToUnmark = indexToUnmark;
        this.list = list;
    }

    /**
     * Executes the unmark command by marking an item as undone in the list.
     *      Gives user information about the item marked as undone.
     *
     * @return String representing information about the item marked as undone.
     */
    @Override
    public String execute() {
        list.markItemUndone(this.indexToUnmark);
        // takes in 0 index
        return Ui.markItemUndoneMessage(list, indexToUnmark - 1);
    }
}
