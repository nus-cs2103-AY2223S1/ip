/**
 * This class encapsulates an add command from the user.
 */
public class AddCommand extends Command {
    private TaskList taskList;
    private Task task;

    AddCommand(TaskList taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Adds the task into the task list.
     *
     * @return A String signalling that the task has been added.
     */
    @Override
    public String execute() {
        this.taskList.add(this.task);
        return "me've added dis task:\n  " + this.task
                + "\nnow you have " + this.taskList.size() + " tasks in da wist uWu";
    }
}
