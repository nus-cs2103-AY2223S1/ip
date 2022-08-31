package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import UI.UI;

/**
 * Encapsulates the unmark command
 */
public class UnmarkCommand extends Commands {
    private int index;

    /**
     * @param index index of item to be unmarked
     */
    public UnmarkCommand(int index){
        this.index = index;
    }

    /**
     * function to unmark the item with the given index
     * function simply calls retrieves the item and
     * calls the completeTask() function on it
     *
     * @param list TaskList to be modified
     * @param storage Storage to be modified
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage, UI ui) {
        Todo item = list.get(index);
        item.unCompleteTask();
        ui.sendTextToUi("Quack! Unmarked item: " + item);
    }
    
    /**
     * see super class
     * @return returns false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
