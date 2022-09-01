package Rabbit.GUI;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import Rabbit.Rabbit;

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

    private Rabbit rabbit;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image rabbitImage = new Image(this.getClass().getResourceAsStream("/images/Rabbit2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRabbit(Rabbit r) {
        rabbit = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rabbit.getResponse(input);
        if (input.equals("change")) {
            Random rd = new Random();
            int randomInt = rd.nextInt(6) + 1;
            String rabbitNumber = "Rabbit" + randomInt + ".PNG";
            rabbitImage = new Image(this.getClass().getResourceAsStream("/images/" + rabbitNumber));
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRabbitDialog(response, rabbitImage)
        );
        userInput.clear();
    }

    /**
     * Shows text in the panel without the user's input
     * when necessary.
     *
     * @param text the text to be shown.
     */
    public void showText(String...text) {
        for (String content : text) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getRabbitDialog(content, rabbitImage)
            );
        }
    }
}
