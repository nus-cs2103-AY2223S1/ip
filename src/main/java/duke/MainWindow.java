package duke;

import java.awt.Color;
import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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

    private Duke duke;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/user.png")));
    private Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/user2.png")));

    /**
     * Initialise the window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.isResizable();

        // Generate the background colour for the Dialog
        java.awt.Color awtColor = Color.cyan;
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = 0.5;
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);

        dialogContainer.getStyleClass().add("color-palette");
        dialogContainer.setBackground(new Background(new BackgroundFill(fxColor, CornerRadii.EMPTY, Insets.EMPTY)));

        String startingText = "Hello! I'm Dukity Duke! Type 'help1' to see "
                + "a list of useful commands, and "
                + "'help2' to see a list of commands to create tasks! Type 'bye' to quit!";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(startingText, dukeImage)
        );
        userInput.clear();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
