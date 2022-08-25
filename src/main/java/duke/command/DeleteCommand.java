package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        String deletedTaskDesc = tasklist.getTaskN(index).toString();
        tasklist.deleteTaskN(index);
        ui.printDeleteTaskReply(deletedTaskDesc, tasklist.getNumOfTask());
        storage.overwriteFile(tasklist.toFile());
    }
}
