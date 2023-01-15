package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;
import javafx.application.Platform;

/**
 *  Bye command created to end the program.
 */
public class ByeCommand extends Command {


    /**
     *  Runs the Bye command to show the goodbye message.
     *
     * @param tasks TaskList object of Blink object
     * @param ui Ui object of Blink object
     * @param storage Storage object of Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return "";
    }
}
