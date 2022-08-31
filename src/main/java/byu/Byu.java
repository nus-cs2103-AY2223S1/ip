package byu;

import commands.Command;
import exceptions.DukeException;
import java.io.IOException;

/**
 * Byu is a chatbot that helps to organize tasks.
 */
public class Byu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Byu.
     *
     * @param filePath String representation of a filePath.
     * @return an instance of Byu.
     */
    public Byu(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = storage.load();
        } catch (IOException e) {
        }
    }

    /**
     * Runs Byu and scans for user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Byu("./data/Duke.txt").run();
    }

}
