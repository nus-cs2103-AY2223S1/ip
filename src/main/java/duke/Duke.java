package duke;

import duke.commands.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke program for keeping track of Tasks.
 */
public class Duke {
    protected static boolean terminate = false;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    /**
     * Duke constructor.
     *
     * @param filePath filePath of file to store Task information.
     * @param tempFilePath filePath of temporary file to store information for rewriting.
     */
    public Duke(String filePath, String tempFilePath) {
        ui = new Ui();
        storage = new Storage(filePath, tempFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initialises files and calls method to start Duke program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt", "data/temp.txt");
        duke.run();
    }

//    @Override
//    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }
}
