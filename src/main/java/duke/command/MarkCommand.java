package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class MarkCommand extends Command {
    private int num;

    public MarkCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.getArr().get(this.num - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("How to mark something that is not inside??");
        }
        ui.sayMarked(this.num, tasks.getArr());
        storage.overwrite();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
