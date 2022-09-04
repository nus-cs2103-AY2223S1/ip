import java.util.Arrays;

public class Command {
    private final CommandsList command;
    private final String[] arguments;

    public Command(CommandsList command) {
        this.command = command;
        this.arguments = null;
    }

    public Command(CommandsList command, String argument) {
        this.command = command;
        this.arguments = new String[]{argument};
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Command)) {
            return false;
        }
        Command otherCommand = (Command) obj;
        return otherCommand.getCommand().equals(this.command) &&
                Arrays.equals(otherCommand.getArguments(), this.arguments);
    }
}
