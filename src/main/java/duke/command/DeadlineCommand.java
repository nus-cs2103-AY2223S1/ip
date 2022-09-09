package duke.command;

import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

/**
 * Command to add a {@code Deadline} to the {@code TaskList}
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDate date;

    /**
     * Constructor for {@code DeadlineCommand}
     * @param description the description of the {@code Deadline}
     * @param date the date of the {@code Deadline}
     */
    public DeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * To execute the {@code DeadlineCommand}
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, date);
        tasks.add(deadline);
        ui.deadlineTask(tasks, deadline);
        storage.update(tasks.getTasks());
    }
}
