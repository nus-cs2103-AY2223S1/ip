package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

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
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the current {@code Ui}
     * @param storage the current {@code Storage}
     * @return a string to be printed onto the dialog box
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, date);
        tasks.add(deadline);
        storage.update(tasks.getTasks());
        return ui.deadlineTask(tasks, deadline);
    }
}
