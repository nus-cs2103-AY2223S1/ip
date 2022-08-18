package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    public static final String COMMAND_ID = "DELETE";
    private final int targetIndex;

    public DeleteCommand(int targetIndex) {
        super();
        this.targetIndex = targetIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.removeTaskFromList(this.targetIndex);
        storage.writeDataToFile(taskList);
    }
}
