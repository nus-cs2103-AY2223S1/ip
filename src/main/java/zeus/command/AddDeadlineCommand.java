package zeus.command;

import java.time.LocalDate;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;
import zeus.task.Deadline;


/**
 * Command that handles adding Deadline.
 */
public class AddDeadlineCommand extends Command {

    private String description;
    private String by;
    private LocalDate localDate;

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
     * @param localDate LocalDate that represents due date
     */
    public AddDeadlineCommand(String description, LocalDate localDate) {
        this.description = description;
        this.localDate = localDate;
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
        Deadline deadline;
        if (this.localDate == null) {
            deadline = new Deadline(description, this.by);
        } else {
            deadline = new Deadline(description, this.localDate);
        }
        taskList.addTask(deadline);
        ui.printAddTask(deadline, taskList.size());
        storage.saveTasksToDisk(taskList.getTaskList());
    }
}
