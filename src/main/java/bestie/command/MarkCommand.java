package bestie.command;

import bestie.tasks.ToDoList;
import bestie.utils.Ui;

/**
 * Mark Command class representing the mark command executed by the user.
 */
public class MarkCommand extends Command {
    private int indexToMark;
    private ToDoList list;

    /**
     * Constructs mark command.
     */
    public MarkCommand(int indexToMark, ToDoList list) {
        super("mark");
        this.indexToMark = indexToMark;
        this.list = list;
    }

    /**
     * Executes the mark command by marking an item as done in the list.
     *      Gives user information about the item marked as done.
     *
     * @return String representing information about the item marked as done.
     */
    @Override
    public String execute() {
        list.markItemDone(this.indexToMark);
        // takes in 0 index
        return Ui.markItemDoneMessage(list, indexToMark - 1);
    }
}
