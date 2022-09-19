package pikachu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    private Button closeButton;
    @FXML
    private Button sendButton;

    private Pikachu pikachu;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/squirtle/happy.png"));
    private Image pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/a_bit_happy.png"));

    private final Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/background.png"));

    /**
     * Initialise the main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        Background background = new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.ROUND,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)));
        dialogContainer.setBackground(background);
        sendButton.setFont(Font.font("Verdana"));
        sendButton.setStyle("-fx-background-color: #8B5B5B; -fx-text-fill: white; -fx-background-radius: 10;");
        userInput.setFont(Font.font("Verdana"));
    }

    public void setPikachu(Pikachu d) {
        pikachu = d;
        dialogContainer.getChildren().add(DialogBox.getPikachuDialog(pikachu.sayHi(), pikachuImage, true));
    }

    public void setPikachuImage(PikachuEmotion emotion) {
        switch (emotion) {
        case CRY:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/cry.png"));
            break;
        case CONFUSED:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/confused.png"));
            break;
        case OK:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/ok.png"));
            break;
        case SAD:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/sad.png"));
            break;
        case YEA:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/yea.png"));
            break;
        case PLAY:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/play.png"));
            break;
        case HAPPY:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/happy.png"));
            break;
        default:
            pikachuImage = new Image(this.getClass().getResourceAsStream("/images/pikachu/a_bit_happy.png"));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (pikachu.getIsExit()) {
            closeButtonAction();
        }
        String input = userInput.getText();
        String response = pikachu.getResponse(input);
        PikachuEmotion emotion = pikachu.getEmotion();
        boolean isValidMessage = pikachu.getisValid();;
        this.setPikachuImage(emotion);
        if (!isValidMessage) {

        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPikachuDialog(response, pikachuImage, isValidMessage)
        );
        userInput.clear();
    }

    @FXML
    private void closeButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
