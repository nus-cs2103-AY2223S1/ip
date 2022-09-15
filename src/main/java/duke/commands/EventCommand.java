package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.ui.Ui;

/**
 * This class represents a command to add an event to the task list
 */
public class EventCommand extends Command {
    private final String description;
    private final String at;

    /**
     * Constructs a new Event Command
     * @param description A description of the event
     * @param at When the event is occurring
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Event event = new Event(description, at);
        taskList.addToList(event);
        storage.save(taskList);
        return ui.printAddTask(event, taskList.getSize());
    }
}
