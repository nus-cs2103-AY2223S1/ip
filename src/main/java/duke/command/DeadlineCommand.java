package duke.command;

import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeadlineCommand extends Command {
    private String deadlineDescription;
    private String by;

    public DeadlineCommand(String deadlineDescription, String by) {
        super();
        this.deadlineDescription = deadlineDescription;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(deadlineDescription, by);
        taskList.addToTaskList(deadline);
        ui.showAddTaskMessage(taskList, deadline);
    }
}
