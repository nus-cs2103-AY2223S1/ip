package commands;

import common.ChatResponse;
import tasklist.TaskList;

/**
 * Represents an Invalid command to be executed.
 */
public class InvalidCommand extends Command {

    /**
     * Executes InvalidCommand Command.
     *
     * @param taskList The taskList relevant to the command.
     * @return String with messages from execution.
     */
    @Override
    public String execute(TaskList taskList) {
        return ChatResponse.returnChatInvalidCommand();
    }
}
