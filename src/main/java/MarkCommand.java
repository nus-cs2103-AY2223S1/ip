import java.util.List;

public class MarkCommand extends Command {
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Sorry! The task index is out of bounds. " +
            "Please try again with a valid index.";
    public MarkCommand(String command) {
        super(command);
    }

    @Override
    public String execute(List<Task> tasks) {
        int taskIndex = super.getTaskIndex();
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            return INDEX_OUT_OF_BOUNDS_MESSAGE;
        }
        if (getKeyCommand().equals("mark")) {
            tasks.get(taskIndex).setIsDone(true);
            return "Nice! I've marked this task as done:\n\t" + tasks.get(taskIndex);
        } else {
            tasks.get(taskIndex).setIsDone(false);
            return "Okay, I've marked this task as not done yet:\n\t" + tasks.get(taskIndex);
        }
    }
}
