package pluto.command;

import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

/**
 * Command to display all tasks.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Displays all tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.nTasks() == 0) {
            return ui.print("No tasks added yet.");
        } else {
            return ui.print("Here are the tasks in your list:\n" + ui.print(tasks.toString()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ListCommand) {
            return true;
        }
        return false;
    }

}
