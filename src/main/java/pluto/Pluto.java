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
    /** File to store user data */
    private final String filePath = "PlutoData.txt";
    /**
     * Constructor that initializes global variables.
     */
    public Pluto() {
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (PlutoException e) {
            return ui.showError(e.getMessage());
        }
    }
}
