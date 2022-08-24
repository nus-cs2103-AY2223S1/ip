package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public static final boolean IS_EXIT = false;

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }

    public boolean isExit() {
        return this.IS_EXIT;
    }
}
