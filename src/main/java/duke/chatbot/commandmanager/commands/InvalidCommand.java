package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;

/**
 * Invalid Command class that is used to throw Invalid Command exception.
 */
public class InvalidCommand implements Command {
    @Override
    public String execute(String arguments) throws InvalidArgumentsException {
        return "";
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
