package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task deleted = tasks.delete(this.idx);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.DELETE, deleted);
    }
}
