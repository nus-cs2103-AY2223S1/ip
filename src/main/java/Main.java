import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

//    @FXML
//    private ScrollPane scrollPane;
//    @FXML
//    private VBox dialogContainer;
//    @FXML
//    private TextField userInput;
//    @FXML
//    private Button sendButton;
//
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
//    private Image duck = new Image(this.getClass().getResourceAsStream("/images/duck.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

//            // Handling user input from text field
//            sendButton.setOnMouseClicked((event) -> {
//                handleUserInput();
//            });
//
//            userInput.setOnAction((event) -> {
//                handleUserInput();
//            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    @FXML
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                MessageContainer.getUserMessage(userText, new ImageView(user)),
//                MessageContainer.getBotMessage(dukeText, new ImageView(duck))
//        );
//        userInput.clear();
//    }
//
//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    private String getResponse(String input) {
//        return "Duck heard: " + input;
//    }
}