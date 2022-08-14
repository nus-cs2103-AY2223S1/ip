/**
 * This class encapsulates an add command from the user.
 */
public class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task into the given task list.
     *
     * @param taskList The task list to add the task into.
     * @return A String signalling that the task has been added.
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.add(this.task);
        return "Added task: " + this.task.getContent();
    }
}
