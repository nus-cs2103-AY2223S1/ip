package duke.chatbot.commandmanager.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidIndexException;
import duke.chatbot.commandmanager.commands.exceptions.NoSuchIndexException;

/**
 * Mark Task Command Handler that marks a task in the list of task as completed.
 * Responds with the confirmation message stating that the task has been successfully marked.
 */
public class MarkTaskCommandHandler implements Command {
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the mark task command with a reference to the chatbot.
     *
     * @param chatBot a reference to the chatbot
     */
    public MarkTaskCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    /**
     * Marks a task as completed and responds with a confirmation message.
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

        boolean isMarkedSuccessfully = this.chatBot.getTaskManager().markTask(itemNumber);
        if (isMarkedSuccessfully) {
            return this.chatBot.getPersonality().formulateResponse("mark_task_success");
        } else {
            return this.chatBot.getPersonality().formulateResponse("mark_task_fail");
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

