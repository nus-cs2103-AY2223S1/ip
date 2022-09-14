package duke;

import java.util.InputMismatchException;
import javafx.scene.control.Label;

/**
 * Main duke.Duke class where duke program is ran
 * @author Shaune Ang
 */
public class Duke {
    private String filePath;
    private FileLoaderSaver storage;
    private TaskList taskList;

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
     * Generates response from user input
     * @param input user input
     * @return Response to user input
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
