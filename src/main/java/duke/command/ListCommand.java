package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.printTaskList(tasklist.toString());
    }
}
