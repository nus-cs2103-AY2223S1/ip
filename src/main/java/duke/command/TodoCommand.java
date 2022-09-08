package duke.command;

import duke.task.Task;
import duke.task.ToDo;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 * Created when user inputs "todo".
 */
public class TodoCommand extends Command {
    private String taskDetails;

    /**
     * Constructor for a todo command.
     * @param taskDetails The description of the task.
     */
    public TodoCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Generates and saves a ToDo task in the task list.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, UI ui, TaskList taskList) {
        Task task = new ToDo(taskDetails);
        taskList.add(task);
        storage.save(taskList.list());
        return "Got it. I've added this task:\n" + task
            + "\nNow you have " + taskList.size() + " tasks in the list";
    }
}
