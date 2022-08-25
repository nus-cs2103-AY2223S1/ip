package duke.command;

import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        super(false);
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(deadline);
        ui.printAddTaskReply(deadline.toString(), taskList.getNumOfTask());
        storage.overwriteFile(taskList.toStorageString());
    }

}

