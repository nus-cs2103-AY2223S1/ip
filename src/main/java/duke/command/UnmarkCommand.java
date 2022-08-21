package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.TaskList;


public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int index;
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        list.markTaskUndone(index);
        ui.printMarkTaskUndone(list.retrieveTask(index));
    }
}
