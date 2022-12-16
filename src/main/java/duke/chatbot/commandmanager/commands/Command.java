package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;

/**
 * Interface that provide commands an execute method
 */
public interface Command {
    String execute(String arguments) throws InvalidCommandException, InvalidArgumentsException;
    boolean isValid();
}
