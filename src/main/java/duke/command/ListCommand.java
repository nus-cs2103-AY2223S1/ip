package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Command.wrapper.getListResponse(taskList.toString());
    }
}
