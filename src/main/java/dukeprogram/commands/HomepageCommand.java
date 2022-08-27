package dukeprogram.commands;

import dukeprogram.UiMessage;
import dukeprogram.commands.tasklist.SelectTaskListsCommand;
import exceptions.InvalidCommandException;

/**
 * HomepageCommand is a process that is able to redirect users to facilities of
 * Duke. It requests input from the user to redirect them to the various facilities.
 */
public class HomepageCommand extends Command {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        switch (commandString) {
        case "tasks":
            return new SelectTaskListsCommand();

        case "factory reset":
            return new FactoryResetCommand();

        case "exit":
            return new ExitCommand();

        default:
            throw new InvalidCommandException(commandString,
                    new UiMessage("I can only understand commands in the square brackets here!"));
        }
    }
}
