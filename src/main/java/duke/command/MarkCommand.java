package duke.command;

import duke.Commands;
import duke.DukeException;
import duke.InvalidArgumentException;
import duke.Task;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (index >= taskList.numberOfTasks()) {
            throw new InvalidArgumentException(Commands.MARK);
        }
        Task task = taskList.getTask(index);
        task.markAsDone();
        storage.saveFile(taskList);
        ui.formatMessage("Nice! I've marked this task as done:\n" + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
