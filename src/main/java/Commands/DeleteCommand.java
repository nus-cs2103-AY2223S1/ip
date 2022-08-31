package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import UI.UI;

/**
 * DeleteCommand encapsulates the deletion of an item in the list
 */
public class DeleteCommand extends Commands{
    private int index;

    /**
     *
     * @param index index of item to be deleted
     */
    public DeleteCommand(int index){
        this.index = index;
    }

    /**
     * @param list TaskList for index to be deleted
     * @param storage not really used here but abstract method requires it
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage, UI ui) {
        Todo item = list.removeFromList(index);
        ui.sendTextToUi("Quack! Deleted item: " + item);
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
