package DukeProgram.Commands.TaskList;

import DukeProgram.Commands.Command;
import DukeProgram.Facilities.TaskList;
import DukeProgram.UI.UserInterface;
import DukeProgram.UiMessage;
import Exceptions.InvalidCommandException;
import Exceptions.KeyNotFoundException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

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
