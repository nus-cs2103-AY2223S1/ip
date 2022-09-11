package duke.chatbot.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commands.exceptions.InvalidCommandException;

/**
 * Bye Command handler that executes the bye command by terminating the chatbot.
 * Responds with a goodbye response obtained from the chatbot.
 */
public class ByeCommandHandler implements CommandHandler {
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the bye command with a reference to the chatbot
     *
     * @param chatBot a reference to the chatbot to terminate
     */
    public ByeCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    @Override
    public String execute(String arguments) throws InvalidCommandException {
        if (arguments.length() > 0) {
            throw new InvalidCommandException();
        }
        chatBot.terminate();
        return chatBot.getGoodbyeResponse();
    }
}
