package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.markTaskN(index, false);
        storage.overwriteFile(taskList.toStorageString());
        return Command.wrapper.getUnmarkResponse(taskList.getTaskN(index).toString());

    }
}