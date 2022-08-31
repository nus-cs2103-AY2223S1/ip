package duke;

import duke.command.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Duke program implements a chatbot that
 * helps the user to keep track of various things.
 * <p>
 * The program can take in tasks and add it to a
 * task list, list it out, delete tasks,
 * mark/unmark tasks and save the tasks.
 *
 * @author fungusta
 * @version 0.1
 * @since 2022-08-23
 */
public class Duke extends Application {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private boolean isBye; //Indicates if bye was input by user

    /**
     * Constructor for class Duke.
     *
     * @param folderPath The filepath to the data folder.
     * @param filePath The filepath to the Duke.txt file contained in the data folder.
     */
    public Duke(String folderPath, String filePath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(folderPath, filePath);
        this.isBye = false;
    }

    /**
     * Runs the Duke program.
     * <p>
     * Communicates to the components in the Duke program to process
     * user inputs and produce outputs explaining the results
     */
    public void run() {
        ui.showWelcome();
        ui.showDividerLine();
        storage.startUpPullStorage(ui, taskList);
        while (!isBye && ui.hasInput()) {
            try {
                String input = ui.takeInput();
                ui.showDividerLine();
                Command c = Parser.parseInput(input);
                c.execute(taskList, ui, storage);
                isBye = c.isBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }
    }

//    public static void main(String[] args) {
//        new Duke("./data", "./data/Duke.txt").run();
//    }

    public static void main(String[] args) {
        try {
            new Duke("./data", "./data/Duke.txt").start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
