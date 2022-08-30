package duke.commands;

import duke.gui.GuiText;

/**
 * This class performs the preparatory instructions before Duke is stopped.
 */
public class ByeCommand implements Command {

    /**
     * Executes the bye command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     */
    @Override
    public String execute() {
        javafx.application.Platform.exit();
        return GuiText.formatByeString();
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ByeCommand) {
            return true;
        }
        return false;
    }
}
