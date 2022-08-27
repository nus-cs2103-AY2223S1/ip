package roofus;

import java.io.FileNotFoundException;

import roofus.command.Command;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Roofus is a Personal Assistant Chatbot that
 * helps a person to keep track of various things.
 *
 * @author Darren Wah
 * @version 0.1
 * @since 2022-08-13
 */
public class Roofus extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an instance of Roofus with
     * Ui, Storage and TaskList instance.
     *
     * @param storagePath File path to storage file.
     * @see Ui
     * @see Storage
     * @see TaskList
     */
    public Roofus(String storagePath) {
        this.storage = new Storage(storagePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (FileNotFoundException err) {
            ui.printErrMessage("Required file not found\nRoofus did not load storage data");
        }
    }
    
    public Roofus(){};

    /**
     * Starts Roofus
     */
    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, storage, ui);
                isRunning = c.isRunning();
            } catch (RoofusException err) {
                ui.printErrMessage(err.getMessage());
            }
            if (!isRunning) {
                break;
            }
        }
    }
    
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Initialises storage files and directories
     *
     * @param args
     */
    public static void main(String[] args) {
        new Roofus(System.getProperty("user.home")
                + "/data/roofus.txt").run();
    }
}
