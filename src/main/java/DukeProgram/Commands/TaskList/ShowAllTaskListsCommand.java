package DukeProgram.Commands.TaskList;

import DukeProgram.Commands.Command;
import DukeProgram.Facilities.TaskList;
import DukeProgram.UI.UserInterface;
import DukeProgram.UiMessage;
import Exceptions.InvalidCommandException;

public class ShowAllTaskListsCommand extends Command {

    @Override
    public boolean execute() {
        if (TaskList.getNumberOfTaskLists() == 0) {
            UserInterface.printInStyle("NO TASK LISTS CREATED");
        } else {
            UserInterface.printInStyle(TaskList.getAllTaskListNames());
        }
        return true;
    }
}
