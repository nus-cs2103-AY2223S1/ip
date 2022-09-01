package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.markTaskN(index, true);
        storage.overwriteFile(taskList.toStorageString());
        return Command.wrapper.getMarkResponse(taskList.getTaskN(index).toString());

    }
}
