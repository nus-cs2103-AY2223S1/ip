package duke.command;

import duke.task.TaskList;

/**
 * DeleteCommand class to delete a task
 */
public class DeleteCommand extends Command{
    private int taskNo;

    /**
     * Constructor of delete command.
     *
     * @param taskNo The task number to be deleted.
     */
    public DeleteCommand(int taskNo){
        this.taskNo = taskNo;
    }

    /**
     * Deletes task in the tasklist.
     *
     * @param tasks The task to be executed.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(taskNo);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
