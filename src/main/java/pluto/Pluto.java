package pluto;

import pluto.command.Command;

/**
 * Chat bot.
 */
public class Pluto {
    /** Writer and reader from local file */
    private Storage storage;
    /** List of tasks created bu the user */
    private TaskList tasks;
    /** To display appropriate messages to the user */
    private Ui ui;

    /**
     * Constructor that initializes global variables.
     * @param filePath Path of file to read and write files from.
     */
    public Pluto(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (PlutoException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the programme by taking inputs and displaying relevant outputs.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PlutoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Entry point of the code.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String path = "PlutoData.txt";
        new Pluto(path).run();
    }
}
