package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.launcher.Launcher;

/**
 * Creates main Duke class that runs main program
 */
public class Duke {

    private static final String DIRECTORY = "./data/";
    private static final String FILENAME = "todo.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private static final boolean isGui = true;


    /**
     * Creates new Duke object, initialising tasks array.
     *
     * @param directory Directory for save file
     * @param fileName  File name for save file
     */
    public Duke(String directory, String fileName) {
        ui = new Ui();
        try {
            storage = new Storage(directory, fileName);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
        }
    }

    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(DIRECTORY, FILENAME);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                ui.showPrompt();
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showExit();

    }

    /**
     * Initialises Duke class and runs the main part.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke(DIRECTORY, FILENAME);

        if (isGui) {
            Launcher.main(args);
        } else {
            duke.run();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
