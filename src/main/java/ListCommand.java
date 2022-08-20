import java.util.ArrayList;

public class ListCommand extends Command {
    private static final String lineFormat = "%d: %s";
    private final ArrayList<Task> tasks;

    public ListCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public CommandResult execute() {
        String[] lines = new String[this.tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = String.format(lineFormat, i + 1, this.tasks.get(i));
        }

        String userMessage = String.join("\n", lines);
        return new CommandResult(userMessage, false);
    }
}
