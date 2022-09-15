package duke.command;

import duke.util.Storage;
import duke.task.TaskList;

/** Represents the command to list out Tasks in TaskList that inherits from Command. */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String message = "";
        message += "Here are the tasks in your list:\n";
        message += taskList.printTaskList();
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
