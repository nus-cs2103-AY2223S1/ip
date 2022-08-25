package commands;
import byu.*;
import exceptions.InvalidIndex;

/**
 * Represents a command to list all the tasks in the list.
 */
public class ListCommand extends Command {

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.list();
    }

    public boolean isExit() {
        return false;
    }
}
