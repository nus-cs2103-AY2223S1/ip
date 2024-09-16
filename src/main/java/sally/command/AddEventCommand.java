package sally.command;

import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.Event;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * AddEventCommand class to represent command to add a new event in the task list.
 *
 * @author liviamil
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String at;

    /**
     * Adds deadline command
     *
     * @param description task description
     * @param at event location
     */
    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SallyException {
        Event event = new Event(description, at);
        tasks.addTask(event);
        ui.showAddedTask(tasks);
        storage.savesFile(tasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
