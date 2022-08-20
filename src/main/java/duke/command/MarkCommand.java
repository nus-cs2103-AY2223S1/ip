package duke.command;

import duke.FileStorage;
import duke.task.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int index;
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        list.markTaskDone(index);
        ui.printMarkTaskDone(list.retrieveTask(index));
    }
}
