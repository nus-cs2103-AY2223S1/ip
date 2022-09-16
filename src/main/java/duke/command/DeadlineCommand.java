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
     * Executes the command by creating a new Deadline and adding it to the current TaskList.
     *
     * @param taskList A list of tasks
     * @param ui A ui responsible for printing output to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printAddTask(taskList.addDeadline(this.description, this.by));
        ui.printSizeOfList(taskList.size());
    }
}
