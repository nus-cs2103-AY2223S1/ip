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
     * @param storagePath File path to storage file.
     * @see Ui
     * @see Storage
     * @see TaskList
     */
    public Roofus(String storagePath) {
        this.storage = new Storage(storagePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (FileNotFoundException err) {
            ui.printErrMessage("Required file not found\nRoofus did not load storage data");
        }
    }

    /**
     * Starts Roofus
     */
    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, storage, ui);
                isRunning = c.isRunning();
            } catch (RoofusException err) {
                ui.printErrMessage(err.getMessage());
            }
            if (!isRunning) {
                break;
            }
        }
    }

    /**
     * Initialises storage files and directories
     *
     * @param args
     */
    public static void main(String[] args) {
        new Roofus(System.getProperty("user.home")
                + "/data/roofus.txt").run();
    }
}
