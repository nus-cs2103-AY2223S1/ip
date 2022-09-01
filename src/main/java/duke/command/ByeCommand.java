package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {
    private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    public ByeCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return GOODBYE_MSG;
    }
}
