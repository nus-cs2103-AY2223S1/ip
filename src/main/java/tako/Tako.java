package tako;

import java.io.IOException;

import tako.command.Command;

/**
 * A chatbot named Tako that
 * supports various tasks.
 *
 * @author Alvin Tan Fu Long
 */
public class Tako {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Constructor for Tako with the file path to save data at.
     *
     * @param filePath File path to save data at.
     */
    public Tako(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns Tako's reponse.
     *
     * @param input Input to respond to.
     * @return Tako's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (TakoException | IOException e) {
            ui.showError(e.getMessage());
        }
        return ui.getResponse();
    }

    /**
     * Returns a welcome message.
     *
     * @return Welcome message.
     */
    public String getWelcome() {
        ui.showWelcome();
        return ui.getResponse();
    }

    /**
     * Checks if the program can exit.
     *
     * @return boolean representing check result.
     */
    public boolean isExit() {
        return isExit;
    }
}
