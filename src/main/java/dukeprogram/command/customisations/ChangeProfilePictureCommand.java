package dukeprogram.command.customisations;

import java.awt.Desktop;
import java.io.IOException;
import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.storage.SaveManager;

/**
 * ChangeProfilePictureCommand is a command that opens the explorer window to
 * where the pictures are stored.
 */
public class ChangeProfilePictureCommand extends Command {

    /**
     * Creates a new ChangeProfilePictureCommand
     * @param duke the duke instance that spawned this command
     */
    public ChangeProfilePictureCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) {
        try {
            Desktop.getDesktop().open(SaveManager.getProfilePicturesDirectory());
            duke.sendMessage("Replace the  Duke.png  and  User.png  files with the "
                    + "preferred Duke and User images respectively."
                    + "\n\nThen restart the application for the changes to take effect!"
                    + "\n\n(Note that the original file names must be the same)");
        } catch (IOException e) {
            duke.sendMessage("I couldn't open the folder, try navigating to the save file?");
        }
    }
}
