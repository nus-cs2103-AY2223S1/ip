package duke.commands.tasks;

import duke.commands.CommandResult;
import duke.domain.Task;

public class AddTaskCommand extends BaseTaskCommand {
    private final Task task;
    private String successMessage = "This task has been successfully added!\n";

    /*
     * AddTaskCommand Constructor
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /*
     * (non-Javadoc)
     * 
     * @see duke.commands.BaseCommand#execute()
     */
    @Override
    public CommandResult execute() {
        this.taskList.addTask(task);
        successMessage = String.format("%s%s%s", successMessage, "\n", task);
        return new CommandResult(super.formatOutputString(successMessage));
    }
}
