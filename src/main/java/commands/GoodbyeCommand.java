package commands;

import common.ChatResponse;
import tasklist.TaskList;

/**
 * Represents a Goodbye Command to be executed.
 */
public class GoodbyeCommand extends Command {
    public GoodbyeCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList) {
        this.isExit = true;
        return ChatResponse.returnChatGoodbye();
    }
}
