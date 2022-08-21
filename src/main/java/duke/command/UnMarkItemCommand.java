package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnMarkItemCommand extends Command {
    private int index;

    public UnMarkItemCommand(int index) {
        super(CommandType.UNMARK);
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unMarkItem(index);
        ui.printUnMarkTaskMessage(tasks.getTask(index));
    }
}
