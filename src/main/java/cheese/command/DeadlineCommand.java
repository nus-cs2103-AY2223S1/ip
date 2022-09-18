package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Deadline;
import cheese.task.Task;
import cheese.ui.Response;

/**
 * Represents a user command to create a new deadline.
 */
public class DeadlineCommand extends Command {
    /** Description of new deadline. */
    private String description;

    /** Deadline of new deadline. */
    private String deadline;

    /**
     * Constructs an instance of <code>DeadlineCommand</code>.
     *
     * @param description Description of new deadline.
     * @param deadline    Deadline of new deadline.
     */
    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes operations to create a new deadline, add deadline to list, and save the list.
     *
     * @throws CheeseException If there is an error saving to the file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CheeseException {
        Task addedTask = taskList.add(new Deadline(description, deadline));
        storage.save(taskList);
        return Response.getAddTaskMessage(addedTask, taskList.getSize());
    }
}
