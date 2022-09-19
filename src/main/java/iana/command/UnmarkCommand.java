package iana.command;

import iana.exception.IanaException;
import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command to mark a task in the task list as incomplete.
 */
public class UnmarkCommand extends Command {

    private String taskNum;

    /**
     * Constructor for UnmarkCommand class.
     * 
     * @param taskNum task number to be unmarked.
     */
    public UnmarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws IanaException {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            tasks.unmark(taskNumber);
            return ui.sayTaskUnmarked(tasks.printTaskString(taskNumber));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IanaException("Hey, this task does not exist!! >:C");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}