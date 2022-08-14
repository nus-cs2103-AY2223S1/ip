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
        return "Got it. I've added this task:\n  " + this.task
                + "\nNow you have " + taskList.size() + " task in the list.";
    }
}
