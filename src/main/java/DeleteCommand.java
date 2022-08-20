import java.util.ArrayList;

public class DeleteCommand extends Command {
    private static final String userMessageFormat = "Removing this task!\n  %s\nNow you have %d tasks left.";
    private final int index;
    private final ArrayList<Task> tasks;

    public DeleteCommand(ArrayList<Task> tasks, String arguments) throws DukeException {
        int i = Integer.parseInt(arguments);
        if (i <= 0 || i > tasks.size()) {
            throw DukeException.invalidIndex;
        }
        this.tasks = tasks;
        this.index = i;
    }

    @Override
    public CommandResult execute() {
        Task task = tasks.get(this.index - 1);
        tasks.remove(this.index - 1);
        int numberOfTasks = tasks.size();
        String userMessage = String.format(userMessageFormat, task, numberOfTasks);
        return new CommandResult(userMessage, true);
    }
}
