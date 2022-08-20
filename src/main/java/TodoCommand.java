import java.util.ArrayList;

public class TodoCommand extends Command {
    private static final String userMessageFormat = "Added this todo!\n  %s\nNow you have %d tasks.";
    private final ArrayList<Task> tasks;
    private final Todo todo;

    public TodoCommand(ArrayList<Task> tasks, String arguments) throws DukeException {
        if (arguments.length() < 1) {
            throw Todo.emptyDescription;
        }

        this.tasks = tasks;
        this.todo = new Todo(arguments);
    }

    @Override
    public CommandResult execute() {
        this.tasks.add(this.todo);
        int numberOfTasks = this.tasks.size();
        String userMessage = String.format(userMessageFormat, this.todo, numberOfTasks);
        return new CommandResult(userMessage, true);
    }
}
