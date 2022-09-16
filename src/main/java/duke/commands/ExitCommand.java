package duke.commands;

/**
 * ExitCommand Class
 */
public class ExitCommand implements BaseCommand {
    public static final String COMMAND_WORD = "exit";
    public static final String SUCCESS_STRING = "Thank you for using aladdin services!\n";

    @Override
    public CommandResult execute() {
        return new CommandResult(SUCCESS_STRING);
    }

    public static boolean equals(BaseCommand command) {
        return command instanceof ExitCommand;
    }
}
