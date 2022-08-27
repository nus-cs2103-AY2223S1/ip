package cheese.command;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.task.Event;
import cheese.task.Task;
import cheese.ui.Ui;

/**
 * Represents a user command to create a new event.
 */
public class EventCommand extends Command {
    /** Description of new event. */
    private String description;

    /** Time interval of new event. */
    private String timeInterval;

    /**
     * Constructs an instance of <code>EventCommand</code>
     * 
     * @param description Description of new event.
     * @param timeInterval Time interval of new event.
     */
    public EventCommand(String description, String timeInterval) {
        this.description = description;
        this.timeInterval = timeInterval;
    }

    /**
     * Executes operations to create a new event, add event to list, and save list.
     * 
     * @param {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task addedTask = taskList.add(new Event(description, timeInterval));
        ui.showAddTask(addedTask, taskList.getSize());
        storage.save(taskList);
    }
}
