package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

//@@author dlimyy-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
//with minor modifications

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/image/ZeeUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/image/ZeeDuke.png"));

    /**
     * Initialises the scrollPane vvalue, Ui and displays the
     * greeting from Duke, the Duke Bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Ui ui = new Ui();
        dialogContainer.getChildren().add(DialogBox.getDialog(ui.greet(),
                dukeImage, true));
    }

    /**
     * Initialises the Duke.
     *
     * @param d The Duke to be initialised.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getReply(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getDialog(input, userImage, false),
                DialogBox.getDialog(response, dukeImage, true)
        );
        userInput.clear();
        if (response.equals("So Long, Farewell!")) {
            //@@author dlimyy-reused
            //Reused from https://stackoverflow.com/questions/27334455
            //with minor modifications
            PauseTransition transition = new PauseTransition(Duration.millis(850));
            transition.setOnFinished(event -> Platform.exit());
            transition.play();
            //@@author
        }
    }
}

//@@author
