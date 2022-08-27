package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an EventCommand which extends Command
 */
public class EventCommand extends Command {
    public static final String COMMAND_ID = "EVENT";
    private final Task task;

    /**
     * Constructs a EventCommand
     */
    public EventCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns a string of the event task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current event task execution
     * @see duke.task.Event
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.addTaskToList(task);
        storage.writeDataToFile(taskList);
        return result;
    }
}
