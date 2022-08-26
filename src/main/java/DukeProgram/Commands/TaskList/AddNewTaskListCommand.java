package DukeProgram.Commands.TaskList;

import DukeProgram.Commands.Command;
import DukeProgram.Facilities.TaskList;
import DukeProgram.UiMessage;
import Exceptions.InvalidCommandException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

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
