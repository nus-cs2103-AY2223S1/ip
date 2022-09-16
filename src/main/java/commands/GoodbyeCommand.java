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

    /**
     * Executes Goodbye Command.
     *
     * @param taskList The taskList relevant to the command.
     * @return String with messages from execution.
     */
    @Override
    public String execute(TaskList taskList) {
        this.isExit = true;
        return ChatResponse.returnChatGoodbye();
    }
}
