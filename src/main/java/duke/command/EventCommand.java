package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

/**
 * Event Command is a Command that creates new Event.
 *
 * @author Eugene Tan
 */
public class EventCommand extends Command {
    private String description;
    private LocalDate at;

    /**
     * Constructor for Event Command
     *
     * @param description Description of the Event.
     * @param at the date/time when the event happens.
     */
    public EventCommand(String description, LocalDate at) {
        super();
        this.description = description;
        this.at = at;
    }

    /**
     * Creates a new Event with the given description and date.
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     * @return Add event message
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addEvent(this.description, this.at);
        storage.save(tasks.getTaskListInString());
        return ui.printAdd(task, tasks.getSize());
    }
}
