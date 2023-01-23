package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * The DeadlineCommand class represents the command that adds a deadline to Duke's task list.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Initializes an instance of a DeadlineCommand.
     *
     * @param deadline The deadline to be added to the task list.
     */
    public DeadlineCommand(Deadline deadline) {
        super(false);
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(deadline);
        storage.overwriteFile(taskList.toStorageString());
        return Command.WRAPPER.getAddTaskResponse(deadline.toString(), taskList.getNumOfTask());
    }

}

