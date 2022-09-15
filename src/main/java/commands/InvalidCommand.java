package commands;

import common.ChatResponse;
import tasklist.TaskList;

/**
 * Represents an Invalid command to be executed.
 */
public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList) {
        return ChatResponse.returnChatInvalidCommand();
    }
}
