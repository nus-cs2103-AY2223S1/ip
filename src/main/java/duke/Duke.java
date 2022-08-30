package duke;

import command.Command;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The main class that runs the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Duke extends Application {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes a Duke object with the specified file path to load and store tasks.
     *
     * @param filePath The file path to the local file responsible for loading and saving.
     */

    public Duke() {
        ui = new Ui();
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadLocalData());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    /**
     * Runs the Duke program.
     */

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Label byeWorld = new Label("Bye World!"); // Creating a new Label control
        helloWorld.setFont(Font.font(50));
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        Scene scene2 = new Scene(byeWorld); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

