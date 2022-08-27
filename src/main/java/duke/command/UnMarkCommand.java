package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an UnMarkCommand which extends Command
 */
public class UnMarkCommand extends Command {
    public static final String COMMAND_ID = "UNMARK";
    private final int targetIndex;

    /**
     * Constructs an instance of UnMarkCommand which inherits Command.
     * @param targetIndex refers to the task number on the list.
     * @see Command
     */
    public UnMarkCommand(int targetIndex) {
        super();
        this.targetIndex = targetIndex;
    }

    /**
     * Returns a string of the unmarked task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current unmarked task execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.markTaskAsUnDone(this.targetIndex);
        storage.writeDataToFile(taskList);
        return result;
    }
}
