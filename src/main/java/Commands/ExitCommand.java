package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import UI.UI;

/**
 * Encapsulates the Exit or "bye" command
 */
public class ExitCommand extends Commands{
    public ExitCommand(){
    }

    /**
     * writes the items from the list to the storage,
     * meaning if the user ungracefully exits the program,
     * their list is not saved.
     *
     * @param list TaskList to be read from
     * @param storage Storage to be modified
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage, UI ui) {
        storage.writeListToFile(list);
    }

    /**
     * see commands superclass
     * @return returns true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
