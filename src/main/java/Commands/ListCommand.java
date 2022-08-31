package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import UI.UI;

/**
 * Encapsulates the List command
 */
public class ListCommand extends Commands{
    public ListCommand() {}

    /**
     * execute merely calls the TaskList.printList() function
     * @param list TaskList to be listed
     * @param storage does nothing
     * @throws IndexOutOfBoundsException thrown when no items in list
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage, UI ui) throws IndexOutOfBoundsException {
        ui.sendTextToUi(list.printList());
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
