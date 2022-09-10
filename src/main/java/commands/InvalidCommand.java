package commands;

import common.ChatResponse;
import tasklist.TaskList;

public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList) {
        return ChatResponse.returnChatInvalidCommand();
    }
}
