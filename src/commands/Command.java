package commands;

import input.Input;

/**
 * Represents a Command that the user can input. Acts as a controller to interface with state and return a response to the user.
 */
public abstract class Command {
    String commandName;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    public boolean isCommand(String commandInput) {
        return commandName.equals(commandInput);
    }

    // Each Command implements its own run method to perform its behaviour (validate necessary arguments, interact
    // with some state and return a response
    public abstract CommandResponse run(Input input);
}
