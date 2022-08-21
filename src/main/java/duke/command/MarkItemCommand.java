package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkItemCommand extends Command {
    private int index;

    public MarkItemCommand(int index) {
        super(CommandType.MARK);
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markItem(index);
        ui.printMarkTaskMessage(tasks.getTask(index));
    }
}
