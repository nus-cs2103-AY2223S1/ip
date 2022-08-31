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
     */
    public Duke() {
        try {
            String filePath = "data/tasks.txt";
            ui = new UI();
            storage = new Storage(filePath);
            tasks = storage.syncArrayList();
        } catch (DukeException e) {
            System.out.println(e);
            ui.showLoadingError();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        try {
            Command c =  Parser.parse(input);
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
