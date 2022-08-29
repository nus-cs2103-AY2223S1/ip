package duke;

import duke.command.Command;
import duke.exception.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main file of Duke to be run to initiate the program.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Duke instance by default with no properties initialised
     */
    public Duke() {

    }

    /**
     * Constructs a Duke instance and load the previous stored TaskList from the filePath.
     *
     * @param filePath the path of the .txt file in this project where the TaskList is to be stored.
     * @throws DukeException the parent Exception inherited by self-created exceptions.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            if (taskList.isEmpty()) {
                throw new DukeException();
            }
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Initialise the Duke program.
     */
    public void run() {
        this.ui.showGreetingMessage();
        this.ui.printList(this.taskList);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
        this.ui.showGoodbyeMessage();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
