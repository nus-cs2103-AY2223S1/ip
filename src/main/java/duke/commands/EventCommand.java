package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.storage.exceptions.StorageException;
import duke.tasks.Event;
import duke.ui.Ui;

/**
 * Represents the command to add an Event task to the list of tasks.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String DATE_INDICATOR = " /at ";
    private String description;
    private String date;

    /**
     * Constructor for an EventCommand.
     * @param description The description of the task.
     * @param date The date of the task.
     */
    public EventCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    /**
     * Adds an Event task to the list of tasks.
     * @param taskList List of tasks.
     * @param ui Shows the Task added and the total number of tasks on the list.
     * @param storage Saves the modified list of tasks.
     * @return The message indicating that the Event task has been added and the number of tasks on the list.
     * @throws StorageException If there is an error saving the modified list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws StorageException {
        Event event = new Event(description, date);
        taskList.addTask(event);
        storage.save(taskList);
        return ui.showTaskAdded(event) + ui.showNumberOfTasks(taskList.getNumberOfTasks());
    }
}
