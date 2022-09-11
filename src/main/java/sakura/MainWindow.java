package sakura;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import sakura.Sakura;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Timer;
import java.util.TimerTask;

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

    private Sakura sakura;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image sakuraImage = new Image(this.getClass().getResourceAsStream("/images/DaSakura.jpg"));

    @FXML
    public void initialize() {
        Background background = new Background(new BackgroundFill(Color.valueOf("a93451"), new CornerRadii(0), new Insets(0)));
        Font font = new Font("Lucida Sans Typewriter Regular", 12);
        pane.setBackground(background);
        dialogContainer.setBackground(background);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setFont(font);
        userInput.setFont(font);

        String l = Ui.logo();
        String greetMessage = Ui.greet();
//        System.out.println(javafx.scene.text.Font.getFontNames());
        logo.setText(l);
        logo.setFont(font);
        logo.setFill(Paint.valueOf("FFFFFF"));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetMessage, sakuraImage)
        );
    }

    public void setDuke(Sakura s) {
        sakura = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sakura.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, sakuraImage)
        );

        userInput.clear();

        if (!Sakura.inProgress) {
            userInput.setEditable(false);
            Ui.exitProgram();
        }
    }
}
