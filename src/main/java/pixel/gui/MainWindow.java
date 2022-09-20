package pixel.gui;

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

    /**
     * Initialises the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        handleWelcomeMessage();
        readTasksFromFile("./data/pixel.txt");
    }

    /**
     * Assigns an instance of the Pixel bot to the GUI.
     */
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

    /**
     * Creates a dialogue box for Pixel to deliver his welcome message.
     */
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
            File dataFolder = new File("./data");
            dataFolder.mkdir();
            File tempFile = new File("./data/pixel.txt");
            try {
                tempFile.createNewFile();
            } catch (IOException ex) {
                dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog("Unable to create new file to store tasks \n"
                        + "Please check your storage directory!", pixelImage)
                );
            }
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
     *     has data in invalid format
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
            return checkFormatOfTaskStatus(formattedTask, status);
        }
        case "D": {
            Task formattedTask = new Deadline(description, due, commandWord);
            return checkFormatOfTaskStatus(formattedTask, status);
        }

        case "E": {
            Task formattedTask = new Event(description, due, commandWord);
            return checkFormatOfTaskStatus(formattedTask, status);
        }

        default:
            throw new InvalidTextDataFormatException("Type of task in database is invalid!");
        }
    }

    /**
     * Checks whether the status of a task in the text file is in "Done/ Not Done" format
     *
     * @param formattedTask task that is extracted from text file
     * @param status status String from the text file
     * @return the formattedTask marked as either done or not done according to status
     * @throws InvalidTextDataFormatException the status is not in "Done/ Not Done" format
     */
    private Task checkFormatOfTaskStatus(Task formattedTask, String status) {
        if (status.strip().equals("Done")) {
            formattedTask.markAsDone();
        } else if (status.strip().equals("Not Done")) {
            formattedTask.markAsNotDone();
        } else {
            throw new InvalidTextDataFormatException("Format of Done/ Not Done status is invalid!");
        }
        return formattedTask;
    }

    /**
     * Checks whether the command word of a task in the text file is in "by/ at" format
     *
     * @param commandWordFromText command word of task from text file
     * @return the command word if it's in correct format
     * @throws InvalidTextDataFormatException if command word is not in "by/ at" format
     */
    private String handleCommandWord(String commandWordFromText) {
        if ((commandWordFromText.strip().equals("at")) || (commandWordFromText.strip().equals("by"))) {
            return commandWordFromText;
        } else {
            throw new InvalidTextDataFormatException("Oops! Please ensure the command word in the text document "
                + "is either \"at\" or \"by\". ");
        }
    }

}
