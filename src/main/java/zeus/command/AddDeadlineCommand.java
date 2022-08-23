package zeus.command;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;
import zeus.task.Deadline;

import java.time.LocalDate;

/**
 * Command that handles adding Deadline.
 */
public class AddDeadlineCommand extends Command {

    private String description;
    private String by;
    private LocalDate ld;

    /**
     * Constructor of AddDeadlineCommand class.
     *
     * @param description Description of Deadline task
     * @param by String that represents due date
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Constructor of AddDeadlineCommand class.
     *
     * @param description Description of Deadline task
     * @param ld LocalDate that represents due date
     */
    public AddDeadlineCommand(String description, LocalDate ld) {
        this.description = description;
        this.ld = ld;
    }

    /**
     * Executes the command of adding the Deadline.
     *
     * @param taskList List of tasks
     * @param ui The Ui
     * @param storage The Storage
     * @throws ZeusException If input is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        Deadline d;
        if (this.ld == null) {
            d = new Deadline(description, this.by);
        } else {
            d = new Deadline(description, this.ld);
        }
        taskList.addTask(d);
        ui.printAddTask(d, taskList.size());
        storage.saveTasksToDisk(taskList.getTaskList());
    }
}
