package duke.command;

import duke.task.TaskList;

/**
 * Mark command class to mark a task as done.
 */
public class MarkCommand extends Command{
    private int taskNo;

    /**
     * Constructor for markCommand class.
     *
     * @param taskNo The task number to be marked.
     */
    public MarkCommand(int taskNo){
        this.taskNo = taskNo;
    }

    /**
     * Marks the give task as done.
     *
     * @param tasks The task to be executed.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.markTask(taskNo);;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
