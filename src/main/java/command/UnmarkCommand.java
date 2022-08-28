package command;

import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super("unmark");
        this.index = index;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        String message = tasks.unmark(index - 1);
        ui.unmark(message);
        storage.update(tasks.getTasks());
    }
}
