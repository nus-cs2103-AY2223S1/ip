package commands;

import java.util.StringJoiner;

import arguments.Argument;
import exceptions.DukeException;
import input.Input;



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

    private String getDescription(Argument...arguments) {
        StringJoiner joiner = new StringJoiner(Input.DELIMITER);
        joiner.add(commandName);

        for (Argument arg: arguments) {
            assert arg != null : "Null argument in getDescription";
            joiner.add(arg.getUsage());
        }
        return joiner.toString();
    }
    protected String makeUsage(Argument ...arguments) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(usageDescription);
        joiner.add("Usage: " + getDescription(arguments));
        return joiner.toString();
    }
    public abstract String getUsageDescription();
}
