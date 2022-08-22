package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmarkTask(index);
            ui.showUnmark(tasks.getTask(index));
            storage.save(tasks.saveToStorage());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\tTask number does not exist!");
        }
    }
}
