package gui;

import alan.Alan;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Random;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    Random rng = new Random();
    boolean wantsBanana = false;
    boolean gaveBanana = false;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Alan alan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/monke.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/monke2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBot(Alan alan) {
        this.alan = alan;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        if (alan == null) {
            response = "Monkey boss ain't home right now ):";
        } else {
            switch (input) {
                case "u monke":
                    response = "NO YOU ARE THE MONKE!";
                    break;
                case "hoohoohaha":
                    response = "heeheehoohaha";
                    break;
                case "bruh":
                    response = "bruh";
                    break;
                case "banana":
                    response = "HOOHOOHAHA nomnom";
                    gaveBanana = true;
                    wantsBanana = false;
                    break;
                default:
                    response = alan.getResponse(input);
            }
            if (wantsBanana || rng.nextInt(10) < 1) {
                response = gaveBanana ? "give me more banana!" : "NO! gimme banana first";
                wantsBanana = true;
            }
            gaveBanana = false;
        }

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox botResponse = DialogBox.getBotDialog(response, botImage);

        dialogContainer.getChildren().addAll(
                userDialog,
                botResponse
        );
        userInput.clear();
    }

    @FXML
    public void sendIntro() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog("HOOHOOHAHA\n Gimme banana", botImage)
        );
    }
}