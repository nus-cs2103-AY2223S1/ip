package dukeprogram.command;

import java.util.Iterator;

import exceptions.InvalidCommandException;

/**
 * ContinuableCommand is an interface that lets a command continue execution
 * after receiving intermediate input from the user
 */
public interface ContinuableCommand {
    void continueParse(Iterator<String> elements) throws InvalidCommandException;
}
