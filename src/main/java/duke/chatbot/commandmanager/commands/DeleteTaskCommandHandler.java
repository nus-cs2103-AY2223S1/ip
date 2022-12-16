package duke.chatbot.commandmanager.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidIndexException;
import duke.chatbot.commandmanager.commands.exceptions.NoSuchIndexException;

/**
 * Delete Task Command Handler that deletes a task in the list of task
 * Responds with the confirmation message stating that the task has been deleted.
 */
public class DeleteTaskCommandHandler implements Command {
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the delete command with a reference to the chatbot.
     *
     * @param chatBot a reference to the chatbot
     */
    public DeleteTaskCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    /**
     * Deletes a task and responds with the task that was deleted.
     *
     * @param arguments string of the item number of the task to be marked
     * @return the string of the response stating the successful marking of the task
     * @throws InvalidArgumentsException thrown when an integer is not provided or out of range of the list size.
     */
    @Override
    public String execute(String arguments) throws InvalidArgumentsException {
        if (arguments.length() == 0) {
            throw new InvalidIndexException(this.chatBot.getPersonality());
        }

        int itemNumber = 0;
        try {
            itemNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException exception) {
            throw new InvalidIndexException(this.chatBot.getPersonality());
        }
        if (itemNumber <= 0 || itemNumber > this.chatBot.getTaskManager().getListSize()) {
            throw new NoSuchIndexException(this.chatBot.getPersonality());
        }

        String deletedTask = this.chatBot.getTaskManager().deleteTask(itemNumber);
        String tasksRemaining = String.valueOf(this.chatBot.getTaskManager().getListSize());
        return this.chatBot.getPersonality().formulateResponse("delete_task", deletedTask, tasksRemaining);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

