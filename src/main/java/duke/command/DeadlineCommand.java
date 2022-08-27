package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a DeadlineCommand which extends Command
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_ID = "DEADLINE";
    private final Task task;

    /**
     * Constructs a DeadlineCommand
     */
    public DeadlineCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns a string of the deadline task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current deadline task execution
     * @see duke.task.Deadline
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.addTaskToList(task);
        storage.writeDataToFile(taskList);
        return result;
    }
}
