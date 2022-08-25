package duke.command;

import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * DeadlineCommand adds a deadline to tasks.
 */
public class DeadlineCommand extends Command {
    private String deadlineDescription;
    private String by;

    /**
     * Constructor for DeadlineCommand
     * @param deadlineDescription deadline description.
     * @param by due date of deadline.
     */
    public DeadlineCommand(String deadlineDescription, String by) {
        super();
        this.deadlineDescription = deadlineDescription;
        this.by = by;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(deadlineDescription, by);
        taskList.addToTaskList(deadline);
        ui.showAddTaskMessage(taskList, deadline);
    }
}
