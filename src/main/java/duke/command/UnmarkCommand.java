package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int num;

    public UnmarkCommand(int num) {
        super();
        this.num = num;
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.getTask(this.num).markAsUndone();
        ui.showUnmark(list.getTask(this.num));
        storage.saveList(list.save());
    }
}
