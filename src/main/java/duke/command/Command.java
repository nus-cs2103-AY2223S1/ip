package duke.command;

import duke.task.TaskList;

/**
 * Abstract class command.
 */
public abstract class Command {


    /**
     * To execute the command.
     * @param tasks The tasks to be executed.
     * @return The response from the bot
     */
    public abstract String execute(TaskList tasks);

    /**
     * @inheritDoc
     */
    public boolean isExit() {
        return false;
    };
}
