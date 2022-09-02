package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** Represents the command to list out Tasks in TaskList that inherits from Command. */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
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
