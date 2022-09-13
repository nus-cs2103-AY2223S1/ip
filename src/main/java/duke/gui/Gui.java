package duke.gui;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tools.Parser;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class takes care of the interaction with the user using a graphical interface.
 * This class manages the input and output from the Duke system.
 */
public class Gui extends Application {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userPic.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dukePic.png"));
    private Image applicationIcon = new Image(this.getClass().getResourceAsStream("/images/ngnl.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/background.jpg"));

    /**
     * Initialize the FXML controller.
     */
    @FXML
    public void initialize() {
        // Let scrollbar follow the last message
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Let Duke create a dialogue box to greet the user
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(GuiText.formatGreetString(), dukeImage));
        Background background = new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, true, false)));
        dialogContainer.setBackground(background);
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            scene = new Scene(ap);
            stage.setScene(scene);

            // Window formatting
            stage.setTitle("Shiro Task Manager"); // Set the title of the application
            stage.setResizable(false);
            stage.getIcons().add(applicationIcon);

            /* Other possible configurations that are currently unused. */
            // scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            // scrollPane.setFitToWidth(true);
            // dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        double height = userInput.getMaxHeight();
        dialogContainer.setPrefHeight(height);
        userInput.clear();
    }

    public String getResponse(String input) {
        String output;
        try {
            Command command = Parser.parseCommand(input);
            output = command.execute();
        } catch (DukeException e) {
            output = GuiText.formatExceptionString(e);
        }
        return output;
    }
}
