package duke.commands;

import duke.TaskList;
import duke.Storage;

public class ByeCommand extends Command {

    @Override
    public String execute(Storage storage, TaskList tl) {
        tl.closeTaskList();
        String response = "Goodbye! Looking forward to see you again!";
        return response;
    }
}
