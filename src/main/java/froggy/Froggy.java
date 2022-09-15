package froggy;


import froggy.command.Command;
import froggy.exception.DukeException;
import froggy.parser.Parser;
import froggy.storage.Storage;
import froggy.task.TaskList;
import froggy.ui.Ui;


/**
 * The Duke class is the core of the entire program.
 * This is where all the commands are first read.
 */
public class Froggy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Creates a Duke object.
     */
    public Froggy() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Froggy().run();
    }

    /**
     * Executes the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    /**
     * Returns the response generated from the bot.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
        return output;
    }

}
