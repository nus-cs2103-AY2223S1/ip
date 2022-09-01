package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A command that is used to add an Event Task.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class EventCommand extends Command {
    private String description;
    private String when;

    /**
     * Constructor for EventCommand.
     * @param description Description of the Event Task.
     * @param when When the Event will occur.
     */
    public EventCommand(String description, String when) {
        this.description = description;
        this.when = when;
    }

    /**
     * Adds an Event Task to list and returns the toString of the Task added.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return toString of the Task added.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        Task task = list.addEvent(description, when);
        storage.writeToFile(list);
        return Ui.addTask(task);
    }
}
