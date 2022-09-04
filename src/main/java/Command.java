public class Command {
    private final CommandsList command;
    private String[] arguments;

    public Command(CommandsList command) {
        this.command = command;
        this.arguments = null;
    }

    public Command(CommandsList command, String[] arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public CommandsList getCommand() {
        return command;
    }

    public String[] getArguments() {
        return this.arguments;
    }
}
