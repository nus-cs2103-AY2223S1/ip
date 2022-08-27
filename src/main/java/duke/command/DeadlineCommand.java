package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * DeadlineCommand is a Command that creates a new Deadline.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description Description of the Deadline.
     * @param by The date/time when the Deadline is due.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Returns the response from Duke after creating a new Deadline with the given description and time/date.
     *
     * @param tasks tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @return The response from Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addDeadline(description, by);
        storage.save(tasks.saveTasks());
        return ui.showAdd(task, tasks.getSize());
    }
}
