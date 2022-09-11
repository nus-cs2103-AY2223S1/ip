package duke.commands;

/**
 * Represents a list command in the Duke application.
 */
public class ListCommand extends Command {
    /** Command word of the list command. */
    public static final String COMMAND_WORD = "list";
    private static final String LINE_FORMAT = "%d: %s";

    @Override
    public CommandResult execute() {
        assert tasks != null : "Should setData() before calling execute().";
        String[] lines = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = String.format(LINE_FORMAT, i + 1, tasks.getTask(i));
        }

        String userMessage = String.join("\n", lines);
        return new CommandResult(userMessage);
    }
}
