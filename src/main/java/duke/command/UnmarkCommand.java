package duke.command;

import duke.DukeException;
import duke.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmarkTaskAtPos(this.index);
            Task currentTask = tasks.getTask(this.index);
            storage.save(tasks);
            ui.showUnmarked(currentTask);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.getCount() == 0) {
                throw new DukeException(Message.INVALID_ACCESS_EMPTY_TASKLIST);
            } else {
                throw new DukeException(Message.returnTaskNotFound(tasks));
            }
        }
    }

}


