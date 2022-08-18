package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {
    public static final String COMMAND_ID = "MARK";
    private final int targetIndex;

    public MarkCommand(int targetIndex) {
        super();
        this.targetIndex = targetIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.markTaskAsDone(this.targetIndex);
        storage.writeDataToFile(taskList);
    }
}
