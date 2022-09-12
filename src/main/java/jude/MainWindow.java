package jude;

//@@author cheeheng-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The class modelling the main GUI of Jude the chatbot.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jude jude;
    private Image userImage;
    private Image chatbotImage;

    /**
     * Initialises the MainWindow, i.e. the main GUI for Jude the chatbot.
     */
    @FXML
    public void initialize() {
        InputStream userImageResource = this.getClass().getResourceAsStream("/images/User.png");
        InputStream chatbotImageResource = this.getClass().getResourceAsStream("/images"
                + "/ManSmilingBehindWall.jpg");
        assert(userImageResource != null);
        assert(chatbotImageResource != null);
        userImage = new Image(userImageResource);
        chatbotImage = new Image(chatbotImageResource);

        assert scrollPane != null : "Scroll pane cannot be null";
        assert dialogContainer != null : "Dialog container cannot be null";
        assert userInput != null : "User input cannot be null";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the instance of Jude the chatbot attached to this MainWindow. Also displays welcome
     * message.
     *
     * @param jude The instance of Jude the chatbot to use.
     */
    public void setJude(Jude jude) {
        this.jude = jude;
        Ui ui = this.jude.getUi();
        Parser parser = this.jude.getParser();
        String firstMessageString = ui.getWelcomeMessage() + '\n' + parser.remindMe();
        dialogContainer.getChildren().add(DialogBox.getChatbotDialog(firstMessageString,
                chatbotImage));
    }

    /**
     * Handles the user input when the user presses the Enter button or clicks the Send button.
     * Clears the user input after processing.
     *
     * @param actionEvent An event which triggers the method call, such as pressing Enter and
     *                    clicking the Send button.
     * @throws IOException When system I/O fails.
     */
    public void handleUserInput(ActionEvent actionEvent) throws IOException {
        String command = userInput.getText();
        if (command.trim().equals("")) {
            // ignore empty command
            return;
        }
        if (jude.isTerminationCommand(command)) {
            //Code adapted from
            //https://www.javaguides.net/2020/09/javafx-quit-button-example-terminate.html
            // Exit the program when a termination command such as 'bye' is passed to the chatbot.
            Platform.exit();
            return;
        }
        String response = jude.getResponse(command);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(command, userImage),
                DialogBox.getChatbotDialog(response, chatbotImage)
        );
        userInput.clear();
    }
}

//@@author
