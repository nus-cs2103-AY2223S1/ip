package byu;

import commands.Command;
import exceptions.DukeException;
import java.io.IOException;

/**
 * Represents a chatbot that helps to organize tasks.
 */
public class Byu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Byu with data from the file.
     */
    public Byu() {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = storage.load();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Runs Byu and starts scanning for user input.
     * Stops running when "bye" is entered.
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
                ui.showError(e);
            }
        }
    }

    /**
     * Creates a Byu instance and runs Byu.
     */
    public static void main(String[] args) {
        new Byu().run();
    }

}
