package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.tasks.TaskList;
import duke.ui.MainWindow;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The Duke program implements an application that
 * allows users to input tasks, mark tasks as done/undone,
 * and delete tasks.
 *
 * @author Ying Ting Tan
 */
public class Duke extends Application {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/tim.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/minion.png"));


    /**
     * Constructor. Initialises Ui, Parser, Storage and TaskList.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();
        try {
            storage.read(taskList);
        } catch (IOException e) {
            e.toString();
        }
     }


    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();
        loadWindow(stage);
    }


    private void loadWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String input) {
        assert input != null;
        try {
            Command c = parser.parseInput(input, ui, storage, taskList);
            String response = c.execute();
            return response;
        } catch (DukeException e) {
            return ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            return "OOPS!!! Please enter date in YYYY-MM-DD format!";
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! Please make sure index is valid!";
        }
    }

    public String greet() {
        return "Bello! I'm Bob! \n" + "What can I do for you?";
    }
}
