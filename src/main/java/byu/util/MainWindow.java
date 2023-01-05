package byu.util;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends VBox {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Byu byu;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.gif"));
    private final Image byuImage = new Image(this.getClass().getResourceAsStream("/images/panda.png"));
    private final DialogBox welcomeDialogBox = DialogBox.getByuDialog(Ui.LOGO + Ui.WELCOME_MESSAGE, byuImage);

    /**
     * Initializes the main window, and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(welcomeDialogBox);
    }

    public void setByu(Byu b) {
        byu = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Byu's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = byu.generateResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getByuDialog(response.getOutput(), byuImage)
        );
        if (response.isHelp()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/HelpWindow.fxml"));
                AnchorPane ap = fxmlLoader.load();
                Stage root = new Stage();
                root.setMinHeight(300);
                root.setMinWidth(570);
                Scene scene = new Scene(ap);
                root.setTitle("COMMAND SUMMARY");
                root.setScene(scene);
                root.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (response.isExit()) {
            Platform.exit();
        }
        userInput.clear();
    }
}
