public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public CommandResult execute() {
        return new CommandResult("", false, true);
    }
}
