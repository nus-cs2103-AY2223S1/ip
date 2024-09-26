package iana.command;

import iana.exception.IanaException;
import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command that marks task as completed.
 */
public class MarkCommand extends Command {

    /** Task index in the task list */
    private String taskNum;
    
    /**
     * Constructor for MarkCommand class.
     * 
     * @param taskNum task number to be marked as completed.
     */
    public MarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws IanaException {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            tasks.mark(taskNumber);
            String markedTask = tasks.printTaskString(taskNumber);
            return ui.sayTaskMarked(markedTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IanaException("Hey, this task does not exist!! >:C");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}