package duke.chatbot.commandmanager.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;

/**
 * Find Task Command Handler that finds all tasks in the list of tasks containing the given keyword.
 * Responds with the list of tasks that contains the given keyword.
 */
public class FindTaskCommandHandler implements Command {
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the find task command with a reference to the chatbot.
     *
     * @param chatBot a reference to the chatbot
     */
    public FindTaskCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    /**
     * Finds all tasks that contain the given keyword and responds with a list containing such tasks.
     *
     * @param arguments the keyword to search for in all tasks
     * @return the string of the response displaying the list of matching tasks
     * @throws InvalidCommandException thrown when the keyword is empty
     */
    @Override
    public String execute(String arguments) throws InvalidCommandException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException(this.chatBot.getPersonality());
        }

        String matchedTaskList = this.chatBot.getTaskManager().findTask(arguments);
        if (matchedTaskList.length() == 0) {
            return this.chatBot.getPersonality().formulateResponse("find_task_empty", arguments);
        }
        return this.chatBot.getPersonality().formulateResponse("find_task", matchedTaskList);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

