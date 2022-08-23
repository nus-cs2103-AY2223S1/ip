package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int num;

    public MarkCommand(int num) {
        super();
        this.num = num;
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.getTask(this.num).markAsDone();
        ui.showMark(list.getTask(this.num));
        storage.saveList(list.save());
    }
}
