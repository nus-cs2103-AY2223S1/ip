package iana.command;

import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;

/**
 * Command to mark a task in the task list as incomplete.
 */
public class UnmarkCommand extends Command {
    public String taskNum;

    /**
     * Constructor for UnmarkCommand class.
     * @param taskNum task number to be unmarked.
     */
    public UnmarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Runs command to unmark a task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            tasks.unmark(taskNumber);
            String unmarkedMsg = "Task unmarked! Remember to mark it once completed! ^_^\n";
            ui.echo(String.format("%s\t   %s", unmarkedMsg, tasks.printTaskString(taskNumber)));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.echo(e.getMessage());
        }
    }

    /**
     * Returns false as command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}