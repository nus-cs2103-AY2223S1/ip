package roofus;

import java.io.FileNotFoundException;

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
            ui.printErrMessage("Required file not found\n" +
                    "Roofus did not load storage data");
        }
    }

    /**
     * Formats and returns Roofus's greetings.
     */
    public String greet() {
        return ui.greet();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(taskList, storage, ui);
        } catch (RoofusException err) {
            return ui.printErrMessage(err.getMessage());
        }
    }
}
