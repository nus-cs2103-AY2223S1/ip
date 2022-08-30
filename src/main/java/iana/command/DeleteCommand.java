package iana.command;

import iana.exception.IanaException;
import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.Task;
import iana.tasks.TaskList;

/**
 * Command that deletes a task.
 */
public class DeleteCommand extends Command {
    private String taskNum;
    
    /**
     * Constructor for DeleteCommand class.
     * @param taskNum the task number to be deleted from the task list.
     */
    public DeleteCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Runs the command to delete task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            Task deletedTask = tasks.delete(taskNumber);
            ui.sayTaskDeleted(deletedTask, tasks.size());
        } catch (IanaException e) {
            ui.echo(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ui.echo("This task number does not exist! ^^");
        } catch (NumberFormatException e) {
            ui.echo("Oops! Delete a task number instead <[u_u]>");
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