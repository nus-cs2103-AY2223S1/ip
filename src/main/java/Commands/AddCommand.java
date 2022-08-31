package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import UI.UI;

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
    public void execute(TaskList<Todo> list, Storage storage, UI ui) {
        list.addToList(item);
        ui.sendTextToUi("Quack! Added new item: " + item);
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
