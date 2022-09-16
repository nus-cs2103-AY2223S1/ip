package bestie.command;

import bestie.tasks.ToDoList;
import bestie.utils.Ui;

/**
 * List Command class representing list command executed by the user.
 */
public class ListCommand extends Command {
    private ToDoList list;

    /**
     * Constructs list command.
     */
    public ListCommand(ToDoList list) {
        super("list");
        this.list = list;
    }

    /**
     * Executes the list command by returning a String representing all items in the list.
     *
     * @return String representing all items in the list.
     */
    @Override
    public String execute() {
        return Ui.listMessage(list);
    }
}
