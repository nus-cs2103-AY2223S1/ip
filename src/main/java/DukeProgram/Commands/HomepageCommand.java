package dukeprogram.commands;

import dukeprogram.commands.tasklist.SelectTaskListsCommand;
import dukeprogram.UiMessage;
import exceptions.InvalidCommandException;

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
