package duke.commands;

public class ExitCommand implements BaseCommand {
    // --Commented out by Inspection (26/8/22, 1:09 AM):public static final String
    // COMMAND_WORD = "bye";

    @Override
    public CommandResult execute() {
        String successMessage = "Goodbye:\n";
        return new CommandResult(successMessage);
    }

    public static boolean equals(BaseCommand command) {
        return command instanceof ExitCommand;
    }

}
