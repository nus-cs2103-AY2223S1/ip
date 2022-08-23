package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_ID = "DELETE";
    private final int targetIndex;

    /**
     * Contructs an instance of DeleteCommand which inherits Command
     * @param targetIndex number on the list to be deleted
     * @return DeleteCommand object
     * @see Command
     */
    public DeleteCommand(int targetIndex) {
        super();
        this.targetIndex = targetIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.removeTaskFromList(this.targetIndex);
        storage.writeDataToFile(taskList);
        return result;
    }
}
