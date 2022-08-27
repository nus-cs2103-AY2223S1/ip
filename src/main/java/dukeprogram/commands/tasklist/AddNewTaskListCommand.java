package dukeprogram.commands.tasklist;

import dukeprogram.commands.Command;
import dukeprogram.facilities.TaskList;
import exceptions.InvalidCommandException;

/**
 * AddNewTaskListCommand is capable of adding new Task Lists to the already
 * existing collection of task lists only if a parsable task list name is provided
 */
public class AddNewTaskListCommand extends Command {

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        return new Command() {

            @Override
            public boolean execute() {
                TaskList.addNewTaskList(commandString);
                return true;
            }
        };
    }
}
