package byu;

import java.io.IOException;

import commands.Command;
import exceptions.DukeException;
import exceptions.DuplicateException;
import exceptions.IncorrectFileInputException;

/**
 * Byu is a chatbot that helps to organize tasks.
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
            storage = new Storage(ui);
            tasks = storage.load();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        } catch (IncorrectFileInputException e) {
            System.out.print(e.getMessage());
        } catch (DuplicateException e) {
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
                storage.save();
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

    /**
     * Generates a response to user input.
     *
     * @param input the user input Byu generates a response to.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                this.storage.save();
                return "Aww, see you soon!";
            } else {
                c.execute(this.tasks, this.ui);
                this.storage.save();
                return this.ui.showOutput();
            }
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
