package duke.command;

import duke.task.TaskList;

/**
 * Abstract class command.
 */
public abstract class Command {

    /**
     * To execute the command.
     *
     * @param tasks The tasks to be executed.
     */
    public abstract void execute(TaskList tasks);

    /**
     * @inheritDoc
     */
    public abstract boolean isExit();
}
