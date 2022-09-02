package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

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
    public String execute(TaskList taskList, Ui ui, Storage s) {
        taskList.mark(index);
        String str = "Nice! I've marked this task as done:\n" + "  " + taskList.retrieveTask(index).toString() + "\n";
        return str;
    }
}
