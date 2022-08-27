package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

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
     * Displays the message that the event task was added.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        Event event = new Event(this.description, this.period);
        taskList.add(event,storage);
        String message = "Nice! This task has been successfully added!";
        ui.displayCommandMessage(message, event, taskList.getSize());
        ui.printBorder();
    }
}
