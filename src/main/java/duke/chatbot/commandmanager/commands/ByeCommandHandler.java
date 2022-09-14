package duke.chatbot.commandmanager.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;
import duke.chatbot.personality.Personality;

/**
 * Bye Command handler that executes the bye command by terminating the chatbot.
 * Responds with a goodbye response obtained from the chatbot.
 */
public class ByeCommandHandler implements Command {
    private final Personality personality;
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the bye command with a reference to the chatbot and its personality
     *
     * @param personality a reference to the chatbot's personality
     * @param chatBot a reference to the chatbot
     */
    public ByeCommandHandler(Personality personality, ChatBot chatBot) {
        this.personality = personality;
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
            throw new InvalidCommandException(this.personality);
        }
        chatBot.terminate();
        return personality.formulateResponse("bye", "");
    }
}
