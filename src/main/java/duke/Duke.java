package duke;

import java.io.IOException;
import java.util.function.BiFunction;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Defines <Code>Duke</Code> class.
 * <p>Main class for the Duke application.</p>
 */
public class Duke extends Application {
    /** Directory path on disk to find/save task list. */
    private static final String OUTPUT_DIRECTORY = "data";

    /** Filename to save task list on disk. */
    private static final String OUTPUT_FILENAME = "list.txt";

    /** <Code>TaskList</Code> to store <Code>Tasks</Code> created by user. */
    private final TaskList taskList = new TaskList();

    /** <Code>Storage</Code> to handle reading and writing task list to disk. */
    private final Storage storage;

    /** <Code>Parser</Code> to parse and handle user inputs. */
    private final Parser parser;

    /** Boolean attribute to know if Duke is running. */
    private boolean dukeIsRunning = false;

    private Scene scene;

    /**
     * Constructor of <Code>Duke</Code> object.
     * @param directory Directory path on disk to find/save task list.
     * @param filename  Filename to save task list on disk.
     */
    public Duke(String directory, String filename) {
        storage = new Storage(directory, filename, taskList);
        parser = new Parser(taskList);
    }

    /**
     * Constructor of <Code>Duke</Code> without arguments.
     * Necessary for JavaFX.
     */
    public Duke() {
        storage = new Storage(OUTPUT_DIRECTORY, OUTPUT_FILENAME, taskList);
        parser = new Parser(taskList);
    }

    /**
     * Loads from <Code>fxml</Code> file to scene and stages scene.
     * @param stage Stage to pass the application scene to.
     */
    @Override
    public void start(Stage stage) {
        dukeIsRunning = true;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource(
                    "/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
            stage.setTitle("Duke");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if Duke is running.
     * @return Boolean object of whether Duke is running.
     */
    public Boolean checkIfRunning() {
        return dukeIsRunning;
    }

    /**
     * Parses a given user input to determine the response
     * to be returned by Duke.
     * @param userInput Input given by user.
     * @return          <Code>String</Code> response by Duke.
     */
    public String getResponse(String userInput) {
        try {
            // Parse input to get command to call. Checks to user input
            // are also made here.
            BiFunction<TaskList, String, String> command =
                    parser.handleUserInputs(userInput);
            // Apply command on input and keep output.
            String output = command.apply(taskList, userInput);
            // Write changes to disk.
            storage.writeToFile(taskList);
            // Return any output.
            if (output.equals("exit sequence initiated")) {
                dukeIsRunning = false;
                return "Bye. Hope to see you again soon!";
            } else {
                return output;
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error writing to file: " + e.getMessage();
        }
    }
}
