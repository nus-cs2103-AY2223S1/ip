package command;

import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

/**
 * Represents command for list keyword
 */
public class ListCommand extends Command {

    /**
     * Instantiates a new list command
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Executes the list command
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.listTask();
        ui.list(listOfTasks);
    }
}
