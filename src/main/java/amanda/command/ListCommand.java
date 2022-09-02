package main.java.amanda.command;

import main.java.amanda.ui.Ui;
import main.java.amanda.manager.TaskList;
import main.java.amanda.manager.StoreManager;

/**
 * ListCommand is a class that lists the current task in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand class
     */
    public ListCommand() {
        super(null, 0);
    }

    /**
     * Iterate through the current task list and prints the tasks.
     * @param tasks the current task list.
     * @param ui the current Ui.
     * @param store the store manager that write any changes to the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, StoreManager store) {
        ui.showListCommand(); // print the messages for ListCommand.
        for (int i = 0; i < tasks.getList().size(); i++) { // iterate through current task list and print each task.
            System.out.println("     " + (i + 1) + "." + tasks.getList().get(i));
        }
    }
}
