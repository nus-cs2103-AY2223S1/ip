public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String lineFormat = "%d: %s";

    @Override
    public CommandResult execute() {
        String[] lines = new String[this.tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = String.format(lineFormat, i + 1, this.tasks.get(i));
        }

        String userMessage = String.join("\n", lines);
        return new CommandResult(userMessage, false, false);
    }
}
