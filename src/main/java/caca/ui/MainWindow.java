package caca.ui;

import caca.CaCa;
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

//@@author carriezhengjr-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with modifications.

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
    private Button quickStartButton;
    @FXML
    private Button detailedGuideButton;

    private CaCa caca;

    // User.png from https://m.duitang.com/blog/?id=1325275817
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    // CaCa.png from https://m.duitang.com/blog/?id=1325275816
    private final Image cacaImage = new Image(this.getClass().getResourceAsStream("/images/CaCa.png"));

    // LightBulb.png from https://similarpng.com/illustration-of-light-bulb-icon-on-transparent-background-png/
    private final Image guideImage = new Image(this.getClass().getResourceAsStream("/images/LightBulb.png"));

    /**
     * Initialises the main window for chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets CaCa instance and displays greeting message to user.
     *
     * @param c CaCa instance
     */
    public void setCaCa(CaCa c) {
        caca = c;

        String displayGreeting = Ui.greet();

        dialogContainer.getChildren().addAll(
                DialogBox.getCaCaDialog(displayGreeting, cacaImage));
    }

    /**
     * Displays quick start to CaCa when user clicks "Quick Start" button.
     */
    @FXML
    public void handleQuickStart() {
        String quickStartmessage = Ui.showQuickStart();
        dialogContainer.getChildren().addAll(
                DialogBox.getCaCaDialog(quickStartmessage, guideImage));
    }

    /**
     * Displays detailed guide to CaCa when user clicks "Detailed Guide" button.
     */
    @FXML
    public void handleDetailedGuide() {
        String detailedGuidemessage = Ui.showDetailedGuide();
        dialogContainer.getChildren().addAll(
                DialogBox.getCaCaDialog(detailedGuidemessage, guideImage));
    }

    /**
     * Exits the program and close the main window.
     */
    public void exitProgram() {
        Platform.exit();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = caca.getResponse(input);

        if (input.equals("help")) {
            handleDetailedGuide();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getCaCaDialog(response, cacaImage)
            );
        }

        userInput.clear();

        // Solution below adapted from
        // https://github.com/cheehongw/ip/blob/master/src/main/java/duke/MainWindow.java
        if (input.equals("bye")) {
            PauseTransition endingProgram = new PauseTransition(Duration.seconds(1.5));
            endingProgram.setOnFinished(event -> exitProgram());
            endingProgram.play();
        }
    }
}
