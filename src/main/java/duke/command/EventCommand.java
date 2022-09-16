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
     * Executes the EventCommand by creating an Event and adding it to the TaskList.
     *
     * @param taskList A list of tasks
     * @param ui A ui responsible for printing output to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printAddTask(taskList.addEvent(this.description, this.at));
        ui.printSizeOfList(taskList.size());
    }
}
