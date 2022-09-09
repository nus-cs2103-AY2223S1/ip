package duke.commands;

import java.util.Arrays;

/**
 * Command object to store a command and its arguments.
 */
public class Command {
    private final CommandsList command;
    private final String[] arguments;

    /**
     * Constructs a Command with an empty argument list.
     * @param command Type of command constructed, based on the CommandsList enum.
     */
    public Command(CommandsList command) {
        this.command = command;
        this.arguments = null;
    }

    /**
     * Constructs a Command with a single argument.
     * @param command Type of command constructed, based on the CommandsList enum.
     * @param argument The argument for the command as a String.
     */
    public Command(CommandsList command, String argument) {
        this.command = command;
        this.arguments = new String[]{argument};
    }

    /**
     * Constructs a Command with an argument list.
     * @param command Type of command constructed, based on the CommandsList enum.
     * @param arguments The list arguments for the command as a String array.
     */
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
        return otherCommand.getCommand().equals(this.command)
                && Arrays.equals(otherCommand.getArguments(), this.arguments);
    }
}
