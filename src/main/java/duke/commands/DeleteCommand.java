package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int i;
    public DeleteCommand(int i) {
        this.i = i;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.deleteTask(i);
        storage.save(tasks.getTaskList());
        ui.showDelete(t, tasks);
    }
}
