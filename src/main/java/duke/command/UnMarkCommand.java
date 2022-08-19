package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class UnMarkCommand extends Command {
    public static final String COMMAND_ID = "UNMARK";
    private final int targetIndex;

    public UnMarkCommand(int targetIndex) {
        super();
        this.targetIndex = targetIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.markTaskAsUnDone(this.targetIndex);
        storage.writeDataToFile(taskList);
    }
}
