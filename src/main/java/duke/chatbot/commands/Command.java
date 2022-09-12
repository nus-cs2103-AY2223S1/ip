package duke.chatbot.commands;

import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commands.exceptions.InvalidCommandException;

/**
 * Interface that provide commands an execute method
 */
public interface Command {
    public String execute(String arguments) throws InvalidCommandException, InvalidArgumentsException;
}
