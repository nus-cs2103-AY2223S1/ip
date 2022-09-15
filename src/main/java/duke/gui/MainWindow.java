package duke.gui;

import java.util.LinkedList;

import duke.Duke;
import duke.task.Task;
import duke.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    @FXML
    private ImageView displayPicture;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user icon.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bobo icon.png"));
    private Image studiousDukeImage = new Image(this.getClass().getResourceAsStream("/images/bobo studious.png"));
    private Image dukeErrorImage = new Image(this.getClass().getResourceAsStream("/images/bobo error.png"));
    private Image dukeStarImage = new Image(this.getClass().getResourceAsStream("/images/bobo star.png"));

    /**
     * Initialises the MainWindow, called once on the implementing controller when the contents of its
     * associated document have been completely loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayPicture.setImage(new Image(this.getClass().getResourceAsStream("/images/bobo icon.png")));
    }

    public void setDuke() {
        Image boboGreet = new Image(this.getClass().getResourceAsStream("/images/bobo greet.png"));
        duke = new Duke();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.greet(), boboGreet));
    }

    public boolean isStillRunning() {
        return !userInput.isDisabled();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.reply(input);
        ResponseType responseType = response.getResponseType();

        if (responseType.equals(ResponseType.LIST) || responseType.equals(ResponseType.TASK)) {
            LinkedList<HBox> tasklistItemDialogs = new LinkedList<>();
            String replyMessage = response.getResponseMessage();

            if (responseType.equals(ResponseType.LIST)) {
                TaskList tasklist = (TaskList) response.getResponseObject();
                tasklistItemDialogs = tasklist.transform((Task t, Integer i) ->
                        TaskListItemDialog.getTaskListItemDialog(t, i)
                );
            } else {
                Task task = (Task) response.getResponseObject();
                tasklistItemDialogs.push(TaskListItemDialog.getTaskListItemDialog(task, null));
            }

            tasklistItemDialogs.push(DialogBox.getDukeDialog(replyMessage, studiousDukeImage));
            tasklistItemDialogs.push(DialogBox.getUserDialog(input, userImage));
            dialogContainer.getChildren().addAll(tasklistItemDialogs);
        } else if (responseType.equals(ResponseType.PIE_CHART)) {
            String replyMessage = response.getResponseMessage();
            PieChart pieChart = (PieChart) response.getResponseObject();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(replyMessage, dukeStarImage),
                    pieChart
            );
        } else if (responseType.equals(ResponseType.ERROR)) {
            String replyMessage = response.getResponseMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(replyMessage, dukeErrorImage)
            );
        } else {
            String replyMessage = response.getResponseMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(replyMessage, dukeImage)
            );
        }
        userInput.clear();

        if (response.getResponseType() == ResponseType.QUIT) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }
}
