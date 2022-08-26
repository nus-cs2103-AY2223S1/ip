package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDate;

/**
 * DeadlineCommand is the command to add a deadline to the TaskList.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description Description of the task.
     * @param time Time the task is due by.
     */
    public DeadlineCommand(String description, LocalDate time) {
        super();
        this.deadline = new Deadline(description, time);
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     */
    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.deadline);
        ui.showAdd(this.deadline, list.getSize());
        storage.saveList(list.save());
    }
}
