import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private static final String userMessageFormat = "Marked task %d as not done!\n  %s";
    private final int index;
    private final Task task;

    public UnmarkCommand(ArrayList<Task> tasks, String arguments) throws DukeException {
        int i = Integer.parseInt(arguments);
        if (i <= 0 || i > tasks.size()) {
            throw DukeException.invalidIndex;
        }
        this.index = i;
        // Subtract 1 to account for 0-index data structure.
        this.task = tasks.get(this.index - 1);
    }

    @Override
    public CommandResult execute() {
        task.markAsUndone();
        String userMessage = String.format(userMessageFormat, this.index, this.task);
        return new CommandResult(userMessage, true);
    }
}
