package duke.commands;

import duke.*;
import duke.task.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int index;
    public UnmarkCommand(int i) {
        this.index = i;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.unmarkDone(index);
        storage.save(tasks.getTaskList());
        ui.showUnmark(t);
    }
}
