package DukeProgram.Commands.TaskList;

import DukeProgram.Commands.Command;
import DukeProgram.Commands.ExitCommand;
import DukeProgram.Commands.Task.Annotations.MarkTaskCommand;
import DukeProgram.Commands.Task.Annotations.UnmarkTaskCommand;
import DukeProgram.Commands.Task.AddTaskCommand;
import DukeProgram.Commands.Task.DeleteTaskCommand;
import DukeProgram.Commands.Task.ListTasksCommand;
import DukeProgram.Commands.Task.TaskPageCommand;
import DukeProgram.Facilities.TaskList;
import DukeProgram.Parser.Parser;
import DukeProgram.UI.UserInterface;
import DukeProgram.UiMessage;
import Exceptions.InvalidCommandException;
import Exceptions.KeyNotFoundException;

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
