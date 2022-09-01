package commands;

import common.ChatResponse;
import tasklist.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList) {
        return ChatResponse.returnChatTaskList(taskList);
    }
}
