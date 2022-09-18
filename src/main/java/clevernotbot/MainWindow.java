package clevernotbot;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

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

    private CleverNotBot cleverNotBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image cleverNotBotImage = new Image(this.getClass().getResourceAsStream("/images/CleverNotBotNormal.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCleverNotBot(CleverNotBot cNB) {
        cleverNotBot = cNB;
        String greetUserMessage = cleverNotBot.getResponse("greet");
        dialogContainer.getChildren().add(DialogBox.getCleverNotBotDialog(greetUserMessage, cleverNotBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing CleverNotBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cleverNotBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCleverNotBotDialog(response, cleverNotBotImage)
        );
        userInput.clear();
        if (isExit(input)) {
            userInput.setEditable(false);
            userInput.setPromptText("Please press X on the top right hand to exit");
        }
    }

    private boolean isExit(String input){
        return input.equals("bye");
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void aboutButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AboutBot.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("About Us");
            Scene scene = new Scene(root1);
            stage.getIcons().add(new Image("/images/CleverNotBotTaskIcon.png"));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void visitButtonAction() {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/LolfoollorS/ip"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
