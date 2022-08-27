package dukeProgram.commands.tasklist;

import dukeProgram.commands.Command;
import dukeProgram.facilities.TaskList;
import dukeProgram.ui.UserInterface;
import exceptions.InvalidCommandException;
import exceptions.KeyNotFoundException;

public class RemoveTaskListCommand extends Command {

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        return new Command() {
            @Override
            public boolean execute() {
                try {
                    TaskList.removeTaskList(commandString);
                } catch (KeyNotFoundException e) {
                    UserInterface.printInStyle(commandString + " does not exist.");
                }
                return true;
            }
        };
    }
}
