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

    /**
     * Executes MarkCommand by marking chosen task as completed and prints confirmation.
     *
     * @param taskList Task list containing task to be marked.
     * @param ui Ui to retrieve return string from.
     * @param s Storage containing the list of tasks or where tasks should be saved.
     * @return String containing confirmation message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        taskList.mark(index);
        String str = "okies!\n" + "  " + taskList.retrieveTask(index).toString() + "\nhas been marked as done!";
        return str;
    }
}
