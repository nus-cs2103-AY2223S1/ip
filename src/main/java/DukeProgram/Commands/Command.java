package dukeprogram.commands;

import dukeprogram.UiMessage;
import exceptions.InvalidCommandException;

public abstract class Command {

    /**
     * Executes the command. Usually, this command has a looping flow within it and indicates
     * a nested state that may require different choices of input. This will repeat until a
     * terminating command is given.
     * For example, in Task Lists selection, we can choose to add, load, delete, etc. These are
     * different paths we can choose, so there is a loop expected to lock the state of the program
     * to this current process, until an ExitCommand is given to terminate from this loop and exit
     * the state.
     * @return whether this command is expected to continue the loop from which it was called
     */
    public abstract boolean execute();

    /**
     * Interprets a string command and provides a list of comparisons to return an appropriate
     * command to continue the state transitions. All commands that require additional input may
     * call upon parse to interpret the next few commands.
     * For example, the load command should be followed by the name of the object to load, so we
     * may expect to call new LoadCommand().parse(objectName) to understand the entire command.
     * @param commandString the command to be interpreted, its meaning varies with context
     * @return a new Command object that is appropriate for the parsed command
     * @throws InvalidCommandException if the command string could not be correctly parsed
     */
    public Command parse(String commandString) throws InvalidCommandException {
        throw new InvalidCommandException(this, commandString,
                new UiMessage(String.format("I cannot understand %s", commandString))
        );
    }
}
