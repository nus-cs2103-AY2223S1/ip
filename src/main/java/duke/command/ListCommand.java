package duke.command;

import duke.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public String executeWithMessage(TaskList tasks) {
        return tasks.showList();
    }
}
