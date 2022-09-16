package hazell.ui.gui;

import hazell.Hazell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button sendButton;

    private Hazell bot;

    private Gui gui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        gui = new Gui(this);
    }

    public void setBot(Hazell bot) {
        this.bot = bot;
        bot.attachUiInstance(gui);
        gui.attachBotInstance(bot);
        bot.start();
    }

    public void displayUserInput(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
    }
    public void displayBotResponse(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(response, botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        gui.setUserInput(input);
        userInput.clear();
        bot.step();
    }
}
