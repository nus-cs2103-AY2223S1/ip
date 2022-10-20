package duke.command;

import duke.task.TaskList;
import duke.Ui;
import java.time.LocalDate;

/**
 * Class which inherits the Command class for a DeadlineCommand
 *
 * @author kaij77
 * @version 0.1
 */
public class DeadlineCommand extends Command {
    private String description;
    private String note;
    private LocalDate by;

    /**
     * Public constructor for a DeadlineCommand.
     *
     * @param description The description of the Deadline
     * @param by When the Deadline is due
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Public constructor for a DeadlineCommand.
     *
     * @param description The description of the Deadline
     * @param by When the Deadline is due
     * @param note An optional note of the Deadline
     */
    public DeadlineCommand(String description, LocalDate by, String note) {
        this.description = description;
        this.by = by;
        this.note = note;
    }

    /**
     * Executes the command by creating a new Deadline and adding it to the current TaskList.
     *
     * @param taskList A list of tasks
     * @param ui An ui responsible for printing output to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.printAddTask(taskList.addDeadline(this.description, this.by, this.note), taskList.size());
    }
}