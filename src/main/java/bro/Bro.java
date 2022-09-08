package bro;

import bro.command.Command;

/**
 * The class for bot.
 */
public class Bro {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    /**
     * Constructor for the class Bro that initialises global variables.
     * @param filePath String of the locaton of the file.
     */
    public Bro(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BroException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Parses each input and executes the command.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser().parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BroException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    /**
     * Entry point.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Bro bro = new Bro("./bro.Bro.txt");
        bro.run();
    }
}
