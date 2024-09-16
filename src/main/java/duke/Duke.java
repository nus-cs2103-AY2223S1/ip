package duke;

import command.Command;
import exceptions.DukeException;
import javafx.application.Application;
import javafx.stage.Stage;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Main duke.Duke class.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructor.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        try {
            ui = new UI();
            storage = new Storage(filePath);
            tasks = storage.syncArrayList();
        } catch (DukeException e) {
            System.out.println(e);
            ui.showLoadingError();
        }
    }

    @Override
    public void start(Stage stage) {

    }

    /**
     * Handles user input.
     * Returns response retrieved from UI class.
     * Causes System to exit if input corresponds to exit
     * command.
     *
     * @param input user input
     * @return
     */
    public String getResponse(String input) {
        boolean isExit;
        try {
            Command c = Parser.parse(input);
            ui.setCurrentInput(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            if (isExit) {
                System.exit(0);
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        String response = ui.getResponse();
        return response;
    }
}
