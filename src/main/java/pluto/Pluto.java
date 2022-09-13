package pluto;

import pluto.command.Command;
import pluto.controller.DialogBox;

/**
 * Logic of chatbot.
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
     * Initializes global variables.
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
     * Returns response DialogBox for user input.
     * @param input Input by the user.
     * @return Response DialogBox generated.
     */
    public DialogBox getResponse(String input) {
        try {
            Command c = Parser.parse(input.strip());
            return DialogBox.getPlutoDialog(c.execute(tasks, ui, storage));
        } catch (PlutoException e) {
            return DialogBox.getPlutoErrorDialog(ui.showError(e.getMessage()));
        }
    }
}
