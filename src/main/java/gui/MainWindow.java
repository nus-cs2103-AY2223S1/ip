package gui;

import duke.Duke;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import storage.Storage;

import ui.UI;

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

    private Duke duke;

    private final UI ui = new UI();

    private  Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private  Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private  Image reminderImage = new Image(this.getClass().getResourceAsStream("/images/DaReminder.jpg"));

    public MainWindow() {
    }

    @FXML
    public void initialize() {
        String welcomeMessage = ui.welcomeMessage();
        //data from duke.txt
        Storage dukeStorage = new Storage("data/duke.txt");
        String dukeList = dukeStorage.printOutContent();
        //data from reminder.txt
        Storage reminderStorage = new Storage("data/reminder.txt");
        String reminderList = reminderStorage.printOutContent();

        if (!reminderList.equals("")) {
            //get all reminders
            String reminders = "IMPORTANT YOU HAVE THESE TASKS THIS WEEK\n" + reminderList;
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(reminders, reminderImage));
        }
        if (!dukeList.equals("")) {
            String tasks = ui.showGotTask() + dukeList;
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(tasks, dukeImage));
        } else {
            ui.showNoTask();
        }

        dialogContainer.setPrefSize(380, 600);

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}