package duke.ui;

import duke.Duke;
import duke.DukeException;
import duke.Ui;
import duke.command.Command;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
    private AnchorPane pane;
    @FXML
    private Text logo;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window, sets up the background, font and logo
     */
    @FXML
    public void initialize() {
        Background background = new Background(new BackgroundFill(Color.valueOf("203649"),
                new CornerRadii(0), new Insets(0)));
        Font font = Font.loadFont(getClass().getResourceAsStream("/font/LigaSFMonoNerdFont-Medium.otf"), 12);
        pane.setBackground(background);
        dialogContainer.setBackground(background);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setFont(font);
        userInput.setFont(font);

        String l = Ui.logo();
        String greetMessage = Ui.greet();
        logo.setText(l);
        logo.setFont(font);
        logo.setFill(Paint.valueOf("FFFFFF"));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetMessage, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = duke.getResponse(userInput.getText());
        } catch (DukeException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (Command.isExit()) {
            userInput.setEditable(false);
            Ui.exitProgram();
        }
    }
}
