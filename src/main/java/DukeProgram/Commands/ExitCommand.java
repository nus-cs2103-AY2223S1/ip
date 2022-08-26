package DukeProgram.Commands;

import DukeProgram.Duke;
import DukeProgram.Storage.SaveManager;
import DukeProgram.UiMessage;
import Exceptions.InvalidCommandException;
import Utilities.SerializedNamesFormatter;

import java.io.IOException;

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
