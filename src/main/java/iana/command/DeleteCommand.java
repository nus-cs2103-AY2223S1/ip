package iana.command;

import iana.exception.IanaException;
import iana.tasks.Task;
import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command that deletes a task.
 */
public class DeleteCommand extends Command {
    private String taskNum;
    
    /**
     * Constructor for DeleteCommand class.
     * 
     * @param taskNum the task number to be deleted from the task list.
     */
    public DeleteCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Runs the command to delete task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws IanaException {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            Task deletedTask = tasks.delete(taskNumber);
            return ui.sayTaskDeleted(deletedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new IanaException("This task number does not exist! ^^");
        } catch (NumberFormatException e) {
            throw new IanaException("Oops! Delete a task number instead <[u_u]>");
        }
    }

    /**
     * Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}