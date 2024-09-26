package duke.command;

import java.time.LocalDate;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * EventCommand class represents an event command given by user.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDate period;

    /**
     * Constructor of the EventCommand class.
     * Sets the description of the task and period to the local
     * variables.
     *
     * @param description Description of the event.
     * @param period Period that the event is occurring.
     */
    public EventCommand(String description, LocalDate period) {
        this.description = description;
        this.period = period;
    }

    /**
     * Adds the event task from the task list.
     * Returns the message that the event task was added.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that event was added.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        Event event = new Event(this.description, this.period);
        commandHistory.addCommand(this);
        taskList.add(event, storage);
        String message = "Nice! This task has been successfully added!";
        return ui.displayCommandMessage(message, event, taskList.getSize());
    }

    /**
     * Deletes the event that has just been added.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that the event has been deleted.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList, CommandHistory commandHistory) {
        Event deletedEvent = new Event(this.description, this.period);
        taskList.remove(taskList.getSize() - 1, storage);
        String message = "This event is no longer added!";
        return ui.displayCommandMessage(message, deletedEvent, taskList.getSize());
    }
}
