package bro.command;

import java.io.IOException;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;
import bro.task.Task;

/**
 * Adds command to the tasklist.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor of the AddCommand class.
     * @param task Gives the task to the given task variable.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * Adds the given task to the file.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        if (!this.checkDuplicate(tasklist)) {
            tasklist.addTask(this.task);
            try {
                storage.writeToFile(this.task);
            } catch (IOException e) {
                throw new BroException("Couldn't delete task!");
            }
            return ui.printAdd(this.task) + ui.listSize(tasklist);
        }
        return "The task already exist.";
    }
    /**
     * Checks whether the same task is already present.
     * @param tasklist The list of tasks.
     * @return Returns true if no duplicates and false otherwise.
     */
    public boolean checkDuplicate(TaskList tasklist) {
        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).toString().equals(this.task.toString())) {
                return true;
            }
        }
        return false;
    }
}
