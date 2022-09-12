package sally.command;

import java.time.LocalDate;
import java.time.LocalDateTime;

import sally.Sally;
import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.Deadline;
import sally.ui.Ui;
import sally.task.TaskList;

/**
 * AddDeadlineCommand class to represent command to add a new deadline in the task list.
 *
 * @author liviamil
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private String by;
    private LocalDate byDate;

    /**
     * Adds deadline for deadline given in String format
     *
     * @param description task description
     * @param by deadline given
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds deadline for deadline given in LocalDateTime format
     *
     * @param description task description
     * @param byDate deadline given
     */
    public AddDeadlineCommand(String description, LocalDate byDate) {
        this.description = description;
        this.byDate = byDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SallyException {
        Deadline deadline;
        if (byDate == null) {
            deadline = new Deadline(description, by);
        } else {
            deadline = new Deadline(description, byDate);
        }

        tasks.addTask(deadline);
        ui.showAddedTask(tasks);
        storage.savesFile(tasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
