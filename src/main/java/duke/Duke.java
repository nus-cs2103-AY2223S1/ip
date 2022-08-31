package duke;

import command.Command;
import exceptions.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

import java.awt.*;

/**
 * Main duke.Duke class.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Constructor.
     * @param filePath Path where disk file should be created.
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

    /**
     * Runs duke.Duke program.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
