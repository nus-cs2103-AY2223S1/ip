import java.util.List;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        super(command);
    }

    @Override
    public String execute(List<Task> tasks) throws JarvisException {
        int taskIndex = super.getTaskIndex();
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JarvisException("No task found. Please enter a valid task number.");
        }
        Task task = tasks.remove(taskIndex);
        return String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                task, tasks.size());
    }
}
