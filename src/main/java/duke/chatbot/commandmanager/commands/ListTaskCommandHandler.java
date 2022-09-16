package duke.chatbot.commandmanager.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;

/**
 * List Task Command Handler that invokes the list task method of the task manager.
 * Responds with the list of tasks returned by the task manager.
 */
public class ListTaskCommandHandler implements Command {
    private final ChatBot chatBot;

    /**
     * Creates a new handler for the list task command with a reference to the chatbot.
     *
     * @param chatBot a reference to the chatbot
     */
    public ListTaskCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    /**
     * Displays the list of task provided by the task manager.
     *
     * @param arguments string of arguments
     * @return the string of the list of task provided by the task manager
     * @throws InvalidCommandException thrown when there are unwanted arguments
     */
    @Override
    public String execute(String arguments) throws InvalidCommandException {
        if (arguments.length() > 0) {
            throw new InvalidCommandException(this.chatBot.getPersonality());
        }

        String taskListString = this.chatBot.getTaskManager().listTask();
        if (taskListString.length() == 0) {
            return this.chatBot.getPersonality().formulateResponse("list_task_empty");
        } else {
            return this.chatBot.getPersonality().formulateResponse("list_task", taskListString);
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
