package dukeProgram.commands.tasklist;

import dukeProgram.commands.Command;
import dukeProgram.facilities.TaskList;
import dukeProgram.ui.UserInterface;

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
