public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private static final String userMessageFormat = "Added this todo!\n  %s\nNow you have %d tasks.";
    private final Todo todo;

    public TodoCommand(String arguments) throws DukeException {
        if (arguments.length() < 1) {
            throw Todo.emptyDescription;
        }

        this.todo = new Todo(arguments);
    }

    @Override
    public CommandResult execute() {
        this.tasks.addTask(this.todo);
        int numberOfTasks = this.tasks.size();
        String userMessage = String.format(userMessageFormat, this.todo, numberOfTasks);
        return new CommandResult(userMessage, true, false);
    }
}
