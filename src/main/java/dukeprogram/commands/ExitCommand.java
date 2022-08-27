package dukeprogram.commands;

import java.io.IOException;

import dukeprogram.Duke;
import dukeprogram.UiMessage;
import dukeprogram.storage.SaveManager;
import exceptions.InvalidCommandException;
import utilities.SerializedNamesFormatter;

/**
 * The ExitCommand is a general command issued to break out of a currently looping
 * process within Duke. Executing this command automatically serializes the file
 * to disk as well.
 *
 * The ExitCommand does not accept any parsable commands at all.
 */
public class ExitCommand extends Command {
    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        throw new InvalidCommandException(this, commandString,
                new UiMessage("ExitCommand accepts no other commands"));
    }

    @Override
    public boolean execute() {
        try {
            SaveManager.serialize(
                    SerializedNamesFormatter.createFileNameForUser(
                            Duke.getUser().getName()
                    )
            );
        } catch (IOException e) {
            System.out.println("I could not write the file");
        }
        return false;
    }
}
