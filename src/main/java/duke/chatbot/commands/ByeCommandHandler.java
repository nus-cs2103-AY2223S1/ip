package duke.chatbot.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commands.exceptions.InvalidCommandException;

/**
 * Bye Command handler that executes the bye command by terminating the chatbot.
 * Responds with a goodbye response obtained from the chatbot.
 */
public class ByeCommandHandler implements Command {
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the bye command with a reference to the chatbot
     *
     * @param chatBot a reference to the chatbot
     */
    public ByeCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    /**
     * Terminates the chatbot and responds with a goodbye message.
     *
     * @param arguments string of arguments
     * @return the string of the goodbye message provided by the chatbot
     * @throws InvalidCommandException thrown when there are unwanted arguments
     */
    @Override
    public String execute(String arguments) throws InvalidCommandException {
        if (arguments.length() > 0) {
            throw new InvalidCommandException();
        }
        chatBot.terminate();
        return chatBot.getGoodbyeResponse();
    }
}
