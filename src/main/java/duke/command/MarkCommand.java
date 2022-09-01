package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        super();
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(String.format(
                    "Task number %d not found! Unable to mark task.", index + 1));
        }
        if (isDone) {
            tasks.get(index).markDone();
            return String.format("Nice! I've marked task %d as done:\n  %s",
                    index + 1, tasks.get(index).toString());
        } else {
            tasks.get(index).unmarkDone();
            return String.format("OK, I've marked task %d as not done yet:\n  %s",
                    index + 1, tasks.get(index).toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof MarkCommand == false) {
            return false;
        }
        MarkCommand that = (MarkCommand) o;
        return index == that.index &&
                isDone == that.isDone;
    }
}
