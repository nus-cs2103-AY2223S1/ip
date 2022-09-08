package commands;

import arguments.Argument;
import exceptions.DukeException;
import input.Input;

import java.util.StringJoiner;

/**
 * Represents a Command that the user can input. Acts as a controller to interface with state and return a response to
 * the user.
 */
public abstract class Command {
    protected String commandName;
    protected String usageDescription;

    /**
     * Constructs a Command
     * @param commandName Name of command
     * @param usageDescription String with usage description
     */
    public Command(String commandName, String usageDescription) {
        this.commandName = commandName;
        this.usageDescription = usageDescription;
    }

    /**
     * Process the input received and return an appropriate CommandResponse
     * @param input Input argument
     * @return CommandResponse with message and exit boolean
     * @throws DukeException - if any issues occur
     */
    // Each Command implements its own run method to perform its behaviour (validate necessary arguments, interact
    // with some state and return a response
    public abstract CommandResponse run(Input input) throws DukeException;

    private String getDescription(boolean isShort, Argument...arguments) {
        StringJoiner joiner = new StringJoiner(Input.DELIMITER);
        joiner.add(commandName);

        for (Argument arg: arguments) {
            assert arg != null : "Null argument in getDescription";
            joiner.add(isShort ? arg.getShortDescription() : arg.getUsage());
        }
        return joiner.toString();
    }

    /**
     * Returns short description for this command with given arguments
     * @param arguments Arguments for the command
     * @return String describing the command
     */
    protected String makeShortDescription(Argument...arguments) {
        return getDescription(true, arguments);
    }

    protected String makeUsage(Argument ...arguments) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(usageDescription);
        joiner.add("Usage: " + getDescription(false, arguments));
        return joiner.toString();
    }
    public abstract String getShortDescription();
    public abstract String getUsageDescription();
}
