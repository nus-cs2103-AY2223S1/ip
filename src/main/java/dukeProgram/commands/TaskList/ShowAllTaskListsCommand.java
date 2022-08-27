package dukeprogram.commands.tasklist;

import dukeprogram.commands.Command;
import dukeprogram.facilities.TaskList;
import dukeprogram.ui.UserInterface;

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
