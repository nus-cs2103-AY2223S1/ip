package dukeprogram.command;


import dukeprogram.Duke;
import dukeprogram.InternalAction;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

import java.util.Iterator;

/**
 * A general purpose Command that allows the executing without additional
 * input or parsing additional input to create other commands for execution.
 */
public abstract class Command {

    private int numberOfInvokes = 0;
    protected Duke duke;

    /**
     * Creates a command
     * @param duke the instance of duke this is associated to
     */
    public Command(Duke duke) {
        this.duke = duke;
    }

    /**
     * Parses the elements of the user input relevant to this command
     * to continue execution of the method
     * @param elements the continued iterator of elements
     */
    public abstract void parse(Iterator<String> elements)
            throws IncompleteCommandException, InvalidCommandException;
}
