package commands;

import common.ChatResponse;
import tasklist.TaskList;

public class GoodbyeCommand extends Command {
    public GoodbyeCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList) {
        this.exit = true;
        return ChatResponse.returnChatGoodbye();
    }
}
