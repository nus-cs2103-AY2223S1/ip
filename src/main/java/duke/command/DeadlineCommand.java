package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        super(false);
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(deadline);
        storage.overwriteFile(taskList.toStorageString());
        return Command.wrapper.getAddTaskResponse(deadline.toString(), taskList.getNumOfTask());
    }

}

