package roofus;

import java.io.FileNotFoundException;

import javafx.application.Platform;
import roofus.command.Command;

/**
 * Roofus is a Personal Assistant Chatbot that
 * helps a person to keep track of various things.
 *
 * @author Darren Wah
 * @version 0.1
 * @since 2022-08-13
 */
public class Roofus {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an instance of Roofus with
     * Ui, Storage and TaskList instance.
     *
     * @see Ui
     * @see Storage
     * @see TaskList
     */
    public Roofus() {
        this.storage = new Storage(System.getProperty("user.home")
                + "/data/roofus.txt");
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (FileNotFoundException err) {
            taskList = new TaskList();
        }
    }

    /**
     * Formats and returns Roofus' greetings.
     *
     * @return String Returns Roofus' greetings in a string.
     */
    public String greet() {
        return ui.greet();
    }

    /**
     * A method to get Roofus' response.
     *
     * @param fullCommand The string representation of user's input.
     * @return String Returns Roofus' response to user's input.
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            if (!c.isRunning()) {
                Platform.exit();
            }
            return c.execute(taskList, storage, ui);
        } catch (RoofusException err) {
            return ui.printErrMessage(err.getMessage());
        }
    }
}
