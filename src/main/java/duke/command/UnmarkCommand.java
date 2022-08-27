package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            list.unmark(num);
            Task curr = list.getTask(num);
            ui.unmarkMessage(curr);
            storage.save(list);
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }
}
