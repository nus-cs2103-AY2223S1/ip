package zeus.command;

import java.time.LocalDate;
import java.time.LocalDateTime;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;
import zeus.task.Deadline;


/**
 * Handles adding Deadline.
 */
public class AddDeadlineCommand extends Command {

    private String description;
    private String by;
    private LocalDateTime localDateTime;

    /**
     * Constructor of AddDeadlineCommand class.
     *
     * @param description Description of Deadline task.
     * @param by String that represents due date.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Constructor of AddDeadlineCommand class.
     *
     * @param description Description of Deadline task.
     * @param localDateTime LocalDateTime that represents due date.
     */
    public AddDeadlineCommand(String description, LocalDateTime localDateTime) {
        this.description = description;
        this.localDateTime = localDateTime;
    }

    /**
     * Executes the command of adding the Deadline.
     *
     * @param taskList List of tasks.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws ZeusException If input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        taskList.saveCurrentTaskListVersion();

        Deadline deadline;
        if (this.localDateTime == null) {
            deadline = new Deadline(description, this.by);
        } else {
            deadline = new Deadline(description, this.localDateTime);
        }
        taskList.addTask(deadline);
        ui.printAddTask(deadline, taskList.getSize());
        storage.saveTasksToDisk(taskList.getTaskList());
    }
}
