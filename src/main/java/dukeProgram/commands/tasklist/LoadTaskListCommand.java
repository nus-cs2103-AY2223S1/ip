package dukeProgram.commands.tasklist;

import dukeProgram.commands.Command;
import dukeProgram.commands.task.TaskPageCommand;
import dukeProgram.facilities.TaskList;
import dukeProgram.ui.UserInterface;
import exceptions.InvalidCommandException;
import exceptions.KeyNotFoundException;

public class LoadTaskListCommand extends Command {

    @Override
    public boolean execute() {
        return false;
    }


    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        return new Command() {
            @Override
            public boolean execute() {
                try {
                    TaskList.changeTaskList(commandString);
                } catch (KeyNotFoundException e) {
                    UserInterface.printInStyle(
                            "I cannot find a task list called " + commandString);
                    return true;
                }

                return new TaskPageCommand().execute();
            }
        };
    }
}
