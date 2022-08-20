public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String userMessageFormat = "Removing this task!\n  %s\nNow you have %d tasks left.";
    private final int index;

    public DeleteCommand(String arguments) {
        this.index = Integer.parseInt(arguments);
    }

    @Override
    public CommandResult execute() throws DukeException {
        // Check if index is out of bounds.
        if (this.index <= 0 || this.index > this.tasks.size()) {
            throw DukeException.invalidIndex;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = this.tasks.get(this.index - 1);
        this.tasks.remove(this.index - 1);
        int numberOfTasks = this.tasks.size();
        String userMessage = String.format(userMessageFormat, task, numberOfTasks);
        return new CommandResult(userMessage, true, false);
    }
}
