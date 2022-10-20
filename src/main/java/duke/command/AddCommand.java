package duke.command;

import duke.task.Task;


/**
 * A command class that encapsulates the action of adding a specific task into the task list.
 */
public abstract class AddCommand extends Command {
    private final Task taskToAdd;

    /**
     * Constructs an AddCommand instance.
     *
     * @param taskToAdd Task instance that needs to be added
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Gets the task that is going to be added.
     * @return task
     */
    public Task getTask() {
        return taskToAdd;
    }


}
