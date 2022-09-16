package duke.chatbot.commandmanager.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.exceptions.EmptyTaskException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;

/**
 * Update To Do Task Command handler that handles the updating of to do tasks in the
 * list of task managed by the task manager.
 */
public class UpdateTodoTaskCommandHandler implements UpdateCommand {
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the update task command with a reference to the chatbot.
     *
     * @param chatBot a reference to the chatbot
     */
    public UpdateTodoTaskCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    /**
     * Updates the task name of the to do task indicated by the item number.
     *
     * @param itemNumber the item number of the task to be updated
     * @param updatedTodoTaskName the updated task name
     * @return the string of the updated task
     * @throws InvalidArgumentsException
     */
    @Override
    public String execute(int itemNumber, String updatedTodoTaskName) throws InvalidArgumentsException {
        if (updatedTodoTaskName.length() == 0) {
            throw new EmptyTaskException(this.chatBot.getPersonality());
        }
        return this.chatBot.getTaskManager().updateTask(itemNumber, updatedTodoTaskName);
    }
}
