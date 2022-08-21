package duke.commands;

/**
 * Represents a list command in the Duke application.
 */
public class ListCommand extends Command {
    /** Command word of the list command. */
    public static final String COMMAND_WORD = "list";
    private static final String lineFormat = "%d: %s";

    /**
     * Executes the command.
     *
     * @return Result of the execution.
     */
    @Override
    public CommandResult execute() {
        String[] lines = new String[this.tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = String.format(lineFormat, i + 1, this.tasks.getTask(i));
        }

        String userMessage = String.join("\n", lines);
        return new CommandResult(userMessage, false, false);
    }
}
