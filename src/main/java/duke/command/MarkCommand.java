package duke.command;

import duke.TaskList;
import duke.storage.Storage;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {

    }
}
