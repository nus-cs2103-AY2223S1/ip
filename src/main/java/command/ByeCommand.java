package command;

import duke.Ui;

/**
 *  A class which encapsulates the bye command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class ByeCommand extends Command {

    public ByeCommand() {

    }

    /**
     * Gives the user a goodbye message.
     * @return The goodbye message from the UI.
     */
    @Override
    public String execute() {
        return Ui.endingMessage();
    }
}
