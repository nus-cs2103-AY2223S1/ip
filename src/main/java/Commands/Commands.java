package Commands;
import Models.Todo;
import Duck.TaskList;
import Duck.Storage;
import UI.UI;

/**
 * Super class of Commands
 */

public abstract class Commands {

    public Commands(){
    }

    /**
     * abstract function to be implemented by all subclasses
     * @param list TaskList to be modified
     * @param storage Storage to be modified
     * @throws IndexOutOfBoundsException is thrown when accessing a non existent object
     */
    abstract public void execute(TaskList<Todo> list, Storage storage, UI ui) throws IndexOutOfBoundsException;

    /**
     * checks if the command returned is an exit command
     * @return true if it's an exit command, false otherwise
     */
    abstract public boolean isExit();
}
