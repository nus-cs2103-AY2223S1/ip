package bro.controllers;

import java.util.Timer;
import java.util.TimerTask;

import bro.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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

    private bro.Bro bro;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image broImage = new Image(this.getClass().getResourceAsStream("/images/DaBro.png"));

    /**
     * Initialising of the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBroDialog(Ui.welcome(), broImage)
        );
    }
    /**
     * Sets the chatbot for the application.
     * @param d The bro chatbot.
     */
    public void setBro(bro.Bro d) {
        bro = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bro's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bro.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBroDialog(response, broImage)
        );
        userInput.clear();
        //@@author anuanas2007-reused
        //Reused from https://github.com/RezwanArefin01/ip/blob/master/src/main/java/duke/command/ExitCommand.java
        //with minor modifications
        if (response.equals("See you later broo!")) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1000L);
        }
        //@@author
    }
}
