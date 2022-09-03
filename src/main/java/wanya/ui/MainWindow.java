package wanya.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import wanya.Wanya;
import wanya.ui.DialogBox;
import wanya.ui.Ui;

//@@author laxus2308-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
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

    private Wanya wanya;

    //obtained from https://mystickermania.com/sticker-packs/anime/spy-family-anya-peace
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Damian.jpg"));
    //obtained from https://id.pinterest.com/pin/715650197050549040/
    private Image wanyaImage = new Image(this.getClass().getResourceAsStream("/images/Anya.jfif"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setWanya(Wanya w) {
        wanya = w;
    }

    public void showGreeting() {
        dialogContainer.getChildren().add(DialogBox.getWanyaDialog(Ui.greet(), wanyaImage));
    }

    public void showLoading() {
        dialogContainer.getChildren().add(DialogBox.getWanyaDialog(Ui.showLoading(), wanyaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = wanya.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWanyaDialog(response, wanyaImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            //@@author laxus2308-reused
            //Reused from https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
            PauseTransition end = new PauseTransition(Duration.seconds(2));
            end.setOnFinished(event -> Platform.exit());
            end.play();
            //@@author
        }
    }
}
//@@author