package duke.chatbot.command;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;

/**
 * A command to be executed and outputs a result.
 *
 * @author jq1836
 */
public abstract class Command {
    /**
     * A list of tasks
     */
    protected TaskList taskList;

    /**
     * A list of arguments
     */
    protected String arguments;

    /**
     * Returns false. Used to detect whether a command results in the closing of the application.
     *
     * @return false
     */
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Returns a {@link CommandResult} instance after execution.
     *
     * @return The result after executing the command.
     * @throws InvalidInputException If arguments passed to Command is invalid.
     */
    public abstract CommandResult execute() throws InvalidInputException;

    public void initData(TaskList taskList) {
        this.taskList = taskList;
    }
}
