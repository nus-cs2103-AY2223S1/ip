import java.util.List;

public class MarkCommand extends Command {
    public MarkCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JarvisException {
        int taskIndex = super.getTaskIndex();

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JarvisException("No task found. Please enter a valid task number.");
        }

        if (getKeyCommand().equals("mark")) {
            tasks.get(taskIndex).setIsDone(true);
            storage.saveTasks(tasks);
            return "Nice! I've marked this task as done:\n\t" + tasks.get(taskIndex);
        } else {
            tasks.get(taskIndex).setIsDone(false);
            storage.saveTasks(tasks);
            return "Okay, I've marked this task as not done yet:\n\t" + tasks.get(taskIndex);
        }
    }
}
