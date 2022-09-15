package commands;

import common.ChatResponse;
import tasklist.TaskList;

/**
 * Represents a List command to be executed.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList) {
        return ChatResponse.returnChatTaskList(taskList);
    }
}
