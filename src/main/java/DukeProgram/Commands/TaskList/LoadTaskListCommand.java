package dukeprogram.commands.tasklist;

import dukeprogram.commands.Command;
import dukeprogram.commands.task.TaskPageCommand;
import dukeprogram.facilities.TaskList;
import dukeprogram.ui.UserInterface;
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
