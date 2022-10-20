package duke.command;

import duke.task.TaskList;
import duke.Ui;

import java.time.LocalDate;

/**
 * Class which inherits the Command class for a EventCommand
 *
 * @author kaij77
 * @version 0.1
 */
public class EventCommand extends Command {
    private String description;
    private String note;
    private LocalDate at;

    /**
     * Public constructor for an EventCommand.
     *
     * @param description The description of the Event
     * @param at The date and time of the Event
     */
    public EventCommand(String description, LocalDate at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Public constructor for an EventCommand.
     *
     * @param description The description of the Event
     * @param at The date and time of the Event
     * @param note The optional note of the Event
     */
    public EventCommand(String description, LocalDate at, String note) {
        this.description = description;
        this.at = at;
        this.note = note;
    }

    /**
     * Executes the EventCommand by creating an Event and adding it to the TaskList.
     *
     * @param taskList A list of tasks
     * @param ui An ui responsible for printing output to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.printAddTask(taskList.addEvent(this.description, this.at, this.note), taskList.size());
    }
}
