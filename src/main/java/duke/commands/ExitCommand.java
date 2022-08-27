package duke.commands;

/**
 * ExitCommand Class
 */
public class ExitCommand implements BaseCommand {
    @Override
    public CommandResult execute() {
        String successMessage = "Goodbye:\n";
        return new CommandResult(successMessage);
    }

    public static boolean equals(BaseCommand command) {
        return command instanceof ExitCommand;
    }
}
