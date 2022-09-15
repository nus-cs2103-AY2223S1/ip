package duke.command;

import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for list keyword for enumerating task list
 */
public class ListCommand extends Command {

    /**
     * Instantiates a new list command
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Executes the list command for enumerating task list
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     * @return Returns String that contains message to be printed by gui
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.listTask();
        return ui.list(listOfTasks);
    }
}
