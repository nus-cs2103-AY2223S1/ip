package pixel.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pixel.Pixel;
import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Task;
import pixel.task.ToDo;
import pixel.util.InvalidTextDataFormatException;
import pixel.util.Storage;
import pixel.util.UserInterface;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Pixel pixel;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/aragorn.jpg"));
    private Image pixelImage = new Image(this.getClass().getResourceAsStream("/images/gundam.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        handleWelcomeMessage();
        readTasksFromFile("./data/pixel.txt");
    }

    public void setPixel(Pixel pixel) {
        this.pixel = pixel;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pixel.parserParse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, pixelImage)
        );
        userInput.clear();
    }

    @FXML
    private void handleWelcomeMessage() {
        String initialMessage = UserInterface.GREETING_MESSAGE + "\n"
            + UserInterface.PROMPT_MESSAGE;
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(initialMessage, pixelImage)
        );
    }
    /**
     * Adds all tasks from external file to the array list of tasks
     *
     * @param filePath filepath of file to refer to
     * @throws IOException when the filePath is invalid
     */
    private void readTasksFromFile(String filePath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            line = reader.readLine();
            while (line != null) { // reader will continuously read the next line
                Task savedTask = lineToTask(line);
                Storage.INPUT_TASKS.add(savedTask);
                line = reader.readLine();
            }
            reader.close();
            Pixel.resetTaskCount(Storage.INPUT_TASKS.size());
            dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(UserInterface.FILE_LOADED, pixelImage)
            );

        } catch (IOException e) {
            File tempFile = new File("./data/pixel", "pixel.txt");
            dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(UserInterface.FILE_DOES_NOT_EXIST, pixelImage)
            );
        } catch (InvalidTextDataFormatException e) {
            dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(e + "\n"
                    + UserInterface.FILE_TASKS_INVALID, pixelImage)
            );
        }
    }

    /**
     * Converts a task saved in the text file in string format into a Task object
     *
     * @param lineFromDocument every line from the text file, where
     *                         the details of the task are saved
     * @return a new Task converted from the String
     * @throws InvalidTextDataFormatException when the text file to be read from
     * has data in invalid format
     */
    private Task lineToTask(String lineFromDocument) throws InvalidTextDataFormatException {
        String[] componentsOfTask = lineFromDocument.strip().split(" ;;; ");
        String type = "";
        String status = "";
        String description = "";
        String commandWord = "";
        String due = "";

        try {
            type = componentsOfTask[0];
            status = componentsOfTask[1];
            description = componentsOfTask[2];

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTextDataFormatException("Task in the text file doesn't have a type, "
                + "status or description!");
        }

        if ((componentsOfTask.length == 4) && (!Objects.equals(componentsOfTask[3], " ;;;"))) {
            throw new InvalidTextDataFormatException("It seems like the fourth or fifth section of "
                + "one of the tasks in the text file is in bad format");
        }

        if (componentsOfTask.length == 5) {
            commandWord = handleCommandWord(componentsOfTask[3]);
            due = componentsOfTask[4];
        }

        if ((componentsOfTask.length != 5) && (componentsOfTask.length != 4)) {
            throw new InvalidTextDataFormatException(InvalidTextDataFormatException.BAD_TASK_FORMATTING);
        }

        switch (type.strip()) {
            case "T": {
                Task formattedTask = new ToDo(description, due, commandWord);
                return checkFormatOfTask(formattedTask, status);
            }
            case "D": {
                Task formattedTask = new Deadline(description, due, commandWord);
                return checkFormatOfTask(formattedTask, status);
            }

            case "E": {
                Task formattedTask = new Event(description, due, commandWord);
                return checkFormatOfTask(formattedTask, status);
            }

            default: {
                throw new InvalidTextDataFormatException("Type of task in database is invalid!");
            }
        }
    }

    private Task checkFormatOfTask(Task formattedTask, String status) {
        if (status.strip().equals("Done")) {
            formattedTask.markAsDone();
        } else if (status.strip().equals("Not Done")) {
            formattedTask.markAsNotDone();
        } else {
            throw new InvalidTextDataFormatException("Format of Done/ Not Done status is invalid!");
        }
        return formattedTask;
    }

    private String handleCommandWord(String commandWordFromText) {
        if ( (commandWordFromText.strip().equals("at")) || (commandWordFromText.strip().equals("by")) ) {
            return commandWordFromText;
        } else {
            throw new InvalidTextDataFormatException("Oops! Please ensure the command word in the text document "
                + "is either \"at\" or \"by\". ");
        }
    }

}
