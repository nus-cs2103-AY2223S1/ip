package Duke.command;

import Duke.command.Command;
import Duke.tasklist.TaskList;
import Duke.utility.Storage;
import Duke.utility.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.listTask();
        return ui.list(listOfTasks);
    }
}
