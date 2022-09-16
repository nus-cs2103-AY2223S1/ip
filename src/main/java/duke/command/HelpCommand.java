package duke.command;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Gets User Guide opened in browser if user requested.
 */
public class HelpCommand extends Command {
    public static final boolean IS_EXIT = false;

    /**
     * Constructs a HelpCommand instance without any parameter.
     */
    public HelpCommand() {
    }


    /**
     * Launch new tab in browser linked to the User Guide website.
     *
     * @param taskList unused for EmptyCommand.
     * @param ui unused for EmptyCommand.
     * @param storage unused for EmptyCommand.
     * @throws DukeException if the website is unable to launch.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("https://jhchee18.github.io/ip/"));
            }
            return "User Guide is opened in your browser.";
        } catch (IOException | URISyntaxException e) {
            throw new DukeException(new RuntimeException(e).getMessage());
        }
    }

    /**
     * Returns false as Empty is not a terminating Command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return IS_EXIT;
    }
}
