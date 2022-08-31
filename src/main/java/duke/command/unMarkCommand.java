package duke.command;

import duke.task.TaskList;

/**
 * Mark command class to mark a task as undone.
 */
public class UnMarkCommand extends Command{
    private int taskNo;

    /**
     * Constructor for unMarkCommand class.
     *
     * @param taskNo The task number to be marked.
     */
    public UnMarkCommand(int taskNo){
        this.taskNo = taskNo;
    }

    /**
     * Marks the give task as undone.
     *
     * @param tasks The task to be executed.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.unMarkTask(taskNo);;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

