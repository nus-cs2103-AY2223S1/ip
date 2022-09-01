package duke;

import java.util.InputMismatchException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


/**
 * Main duke.Duke class where duke program is ran
 * @author Shaune Ang
 */
public class Duke {
    private String filePath;
    private FileLoaderSaver storage;
    private TaskList taskList;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;
        String fullCommand;
        try {
            fullCommand = input;
            Command c = Parser.parse(fullCommand);
            response = c.execute(taskList, storage);
        } catch (InputMismatchException exception) {
            return "Error: please only input String commands.";
        } catch (IndexOutOfBoundsException exception) {
            return "Index specified out of range, please try again...";
        } catch (Exception exception) {
            return exception.getMessage();
        }
        return response;
    }

    /**
     * duke.Duke Constructor
     *
     * @param filePath path to the saved todolist txt file if available
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        storage = new FileLoaderSaver(filePath);

        try {
            taskList = new TaskList(storage.loadFile());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            taskList = new TaskList();
        }
    }
}
