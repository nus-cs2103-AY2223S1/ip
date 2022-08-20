public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final String userMessageFormat = "Marked task %d as done!\n  %s";
    private final int index;

    public MarkCommand(String arguments) {
        this.index = Integer.parseInt(arguments);
    }

    @Override
    public CommandResult execute() throws DukeException {
        // Check if index is out of bounds.
        if (this.index <= 0 || this.index > this.tasks.size()) {
            throw DukeException.invalidIndex;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = this.tasks.getTask(this.index - 1);
        task.markAsDone();
        String userMessage = String.format(userMessageFormat, this.index, task);
        return new CommandResult(userMessage, true, false);
    }
}
