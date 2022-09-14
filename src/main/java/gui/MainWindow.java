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
    private String originalResponse = null;

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
    private Image bananaImage = new Image(this.getClass().getResourceAsStream("/images/banana.png"));
    private Image bananasImage = new Image(this.getClass().getResourceAsStream("/images/bananas.png"));

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
        String response = "";
        if (alan == null) {
            response = "Monkey boss ain't home right now ):";
        } else {
            switch (input) {
                case "hello":
                    response = "hoohooheehee hello!";
                    break;
                case "u monke":
                    response = "NO YOU ARE THE MONKE!";
                    break;
                case "hoohoohaha":
                    response = "heeheehoohaha";
                    break;
                case "bruh":
                    response = "bruh";
                    break;
                case "monkey see":
                    response = "monkey do HOOHOOHAHA";
                    break;
                case "banana":
                    response = "HOOHOOHAHA nomnom";
                    gaveBanana = true;
                    wantsBanana = false;
                    break;
                case "bananas":
                    response = "MANY BANANAS!!! HOOHOOHAHA NOMNOM ";
                    gaveBanana = true;
                    wantsBanana = false;
                    break;
                default:
                    // while the monke wantsBanana, actual responses will not be created
                    response = !wantsBanana ? alan.getResponse(input) : response;
            }
            if (wantsBanana || rng.nextInt(10) < 1) {
                // only save the original response if it is not alr taken
                originalResponse = originalResponse == null ? response : originalResponse;
                response = gaveBanana ? "give me more banana!" : "NO! gimme banana first";
                wantsBanana = true;
            } else if (!wantsBanana && gaveBanana && originalResponse != null) {
                response = "HOOHOOHAHA nomnom, here you go!\n\n" + originalResponse;
                originalResponse = null;
            }
            gaveBanana = false;
        }


        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        if (input.equals("banana")) {
            userDialog = DialogBox.getUserDialog(input, bananaImage);
        }
        if (input.equals("bananas")) {
            userDialog = DialogBox.getUserDialog(input, bananasImage);
        }
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
                DialogBox.getBotDialog("HOOHOOHAHA\n type \"banana\" or \"bananas\" to give me bananas :D", botImage)
        );
    }
}