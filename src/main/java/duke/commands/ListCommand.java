package duke.commands;

import duke.tasklist.TaskList;

/**
 * Class to represent the list command.
 */
public class ListCommand extends Command {

    /**
     * Calls the method to print out the contents of the stored list.
     */
    @Override
    public void executeCommand() {
        TaskList currList = TaskList.getInstance();
        currList.list();
    }
}
