package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import Duck.UI;

/**
 * AddCommand encapsulates the add a new item to list command
 */
public class AddCommand extends Commands{
    private Todo item;

    /**
     * @param item item to be added
     */
    public AddCommand(Todo item) {
        this.item = item;
    }

    /**
     * @param list TaskList for item to be added
     * @param storage not really used here but abstract method requires it
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage) {
        list.addToList(item);
        UI.addNewItemMessage(item);
    }

    /**
     * see commands superclass
     * @return returns false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
