package duke.command;

import duke.FileStorage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int index;
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        Task task = list.retrieveTask(index);
        list.deleteTask(index);
        ui.printDeletedTask(list, task);
    }
}
