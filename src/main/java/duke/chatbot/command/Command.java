package duke.chatbot.command;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.util.MessageBuilder;

/**
 * A command to be executed and outputs a result.
 *
 * @author jq1836
 */
public abstract class Command {
    protected TaskList taskList;

    protected String arguments;

    protected final MessageBuilder messageBuilder = new MessageBuilder();

    /**
     * Returns false. Used to detect whether a command results in the closing of the application.
     *
     * @return false
     */
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Returns a string which represents the message built.
     *
     * @return A string which represents the message built.
     */
    protected abstract String buildMessage();

    /**
     * Returns a {@link CommandResult} instance after execution.
     *
     * @return The result after executing the command.
     * @throws InvalidInputException If arguments passed to Command is invalid.
     */
    public CommandResult execute() throws InvalidInputException {
        return new CommandResult(buildMessage());
    }

    public void initData(TaskList taskList) {
        this.taskList = taskList;
    }
}
