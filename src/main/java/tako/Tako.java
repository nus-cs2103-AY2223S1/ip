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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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

    public String getWelcome() {
        ui.showWelcome();
        return ui.getResponse();
    }

    public boolean isExit() {
        return isExit;
    }
}
