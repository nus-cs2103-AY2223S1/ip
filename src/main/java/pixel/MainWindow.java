package pixel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pixel.util.UserInterface;

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

    private Pixel pixel;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/aragorn.jpg"));
    private Image pixelImage = new Image(this.getClass().getResourceAsStream("/images/gundam.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        handleWelcomeMessage();
    }

    public void setPixel(Pixel pixel) {
        this.pixel = pixel;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pixel.parserParse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, pixelImage)
        );
        userInput.clear();
    }

    @FXML
    private void handleWelcomeMessage() {

        String initialMessage = UserInterface.GREETING_MESSAGE + "\n"
            + UserInterface.PROMPT_MESSAGE;
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(initialMessage, pixelImage)
        );
//        try {
//            if (pixel.botJustInitialised()) {
//                String initialMessage = UserInterface.GREETING_MESSAGE + "\n"
//                    + UserInterface.PROMPT_MESSAGE;
//                dialogContainer.getChildren().add(
//                    DialogBox.getDukeDialog(initialMessage, pixelImage)
//                );
//            }
//        } catch (RuntimeException exception) {
//            System.out.println(exception);
//        }

    }
    // Unsuccessful attempt to make GUI display welcome message upon initialisation.
    // Keeping code for future reference
}
