package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a DeleteCommand which extends Command
 */
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

    /**
     * Returns a string of delete task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current delete task execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.removeTaskFromList(this.targetIndex);
        storage.writeDataToFile(taskList);
        return result;
    }
}
