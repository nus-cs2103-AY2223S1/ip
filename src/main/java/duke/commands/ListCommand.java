package duke.commands;

/**
 * Represents a list command in the Duke application.
 */
public class ListCommand extends Command {
    /** Command word of the list command. */
    public static final String COMMAND_WORD = "list";
    private static final String LINE_FORMAT = "%d: %s";

    private String getMessage() {
        if (tasks.size() == 0) {
            return "There are no tasks yet!";
        }

        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = String.format(LINE_FORMAT, i + 1, tasks.getTask(i));
        }
        return String.join("\n", lines);
    }

    @Override
    public CommandResult execute() {
        assert tasks != null : "Should setData() before calling execute().";
        String userMessage = getMessage();
        return new CommandResult(userMessage);
    }
}
