package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printUnmark(tasks.get(index));
        storage.saveToFile(tasks.saveList());
    }
}
