package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a MarkCommand which extends Command
 */
public class MarkCommand extends Command {
    public static final String COMMAND_ID = "MARK";
    private final int targetIndex;

    /**
     * Constructs an instance of MarkCommand which inherits Command
     * @param targetIndex number on the list to be deleted
     * @return an instance of MarkCommand
     * @see Command
     */
    public MarkCommand(int targetIndex) {
        super();
        this.targetIndex = targetIndex;
    }

    /**
     * Returns a string of the MarkCommand task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current MarkCommand task execution
     * @see duke.task.TaskList
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.markTaskAsDone(this.targetIndex);
        storage.writeDataToFile(taskList);
        return result;
    }
}
