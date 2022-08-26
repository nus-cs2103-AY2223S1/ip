package commands;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

/**
 * UnMarkCommand marks the chosen task as incomplete.
 */
public class UnMarkCommand extends Command {
    public int index;

    /**
     * Constructor for UnMarkCommand.
     *
     * @param index Index of task to be unmarked.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    /**
     * Executes UnMarkCommand by marking chosen task as incomplete and prints confirmation.
     *
     * @param taskList Task list containing task to be unmarked.
     */
    public void run(TaskList taskList) {
        taskList.unmark(index);
        System.out.println("OK, I've marked this task as not done yet:\n" + "  "
                + taskList.retrieveTask(index).toString());
    }
}
