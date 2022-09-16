package commands;

import common.ChatResponse;
import tasklist.TaskList;

/**
 * Represents a List command to be executed.
 */
public class ListCommand extends Command {

    /**
     * Executes List Command.
     *
     * @param taskList The taskList relevant to the command.
     * @return String with messages from execution.
     */
    @Override
    public String execute(TaskList taskList) {
        return ChatResponse.returnChatTaskList(taskList);
    }
}
