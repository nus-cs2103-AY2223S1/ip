package duke.commands;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.tasks.Event;

/**
 * Represents the command to add an Event task to the list of tasks.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String description;
    private String at;

    /**
     * Constructor for an EventCommand.
     * @param description The description of the task.
     * @param at The date of the task.
     */
    public EventCommand(String description, String at) {
        super();
        this.description = description;
        this.at = at;
    }

    /**
     * Checks if the Command is a ByeCommand.
     * @return False.
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Adds an Event task to the list of tasks.
     * @param taskList List of tasks.
     * @param ui Shows the Task added and the total number of tasks on the list.
     * @param storage Saves the modified list of tasks.
     * @throws DukeException If there is an error saving the modified list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(this.description, this.at);
        taskList.addTask(event);
        storage.save(taskList);
        ui.showTaskAdded(event);
        ui.showNumberOfTasks(taskList.numTasks());
    }
}
