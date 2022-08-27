package dukeProgram.commands.tasklist;

import dukeProgram.commands.Command;
import dukeProgram.facilities.TaskList;
import exceptions.InvalidCommandException;

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
