package duke.chatbot.commands;

import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commands.exceptions.InvalidCommandException;

/**
 * Interface that provide command handlers an execute method
 */
public interface CommandHandler {
    public String execute(String arguments) throws InvalidCommandException, InvalidArgumentsException;
}
