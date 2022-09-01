package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index == -1 || index >= tasks.size()) {
            throw new DukeException(String.format("Task number %d not found! Unable to delete task.", index + 1));
        }
        Task deleted = tasks.remove(index);
        return "Noted. I've removed this task:\n" + deleted.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof DeleteCommand == false) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return index == that.index;
    }
}
