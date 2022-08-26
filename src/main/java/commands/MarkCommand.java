package commands;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

/**
 * MarkCommand marks the chosen task as completed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index Index of task to be marked.
     */
    public MarkCommand(int index) {
      this.index = index;
    }

    @Override
    /**
     * Executes MarkCommand by marking chosen task as completed and prints confirmation.
     *
     * @param taskList Task list containing task to be marked.
     */
    public void run(TaskList taskList) {
        taskList.mark(index);
        System.out.println("Nice! I've marked this task as done:\n" + "  " + taskList.retrieveTask(index).toString());
    }
}
