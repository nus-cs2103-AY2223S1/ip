package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import UI.UI;

/**
 * Encapsulates the mark command
 */
public class MarkCommand extends Commands{
    private int index;

    /**
     * @param index index of item to be marked
     */
    public MarkCommand(int index){
        this.index = index;
    }

    /**
     * function to mark the item with the given index
     * function simply calls retrieves the item and
     * calls the completeTask() function on it
     *
     * @param list TaskList to be modified
     * @param storage Storage to be modified
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage, UI ui) {
        Todo item = list.get(index);
        item.completeTask();
        ui.sendTextToUi("Quack! Marked item: " + item);
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
