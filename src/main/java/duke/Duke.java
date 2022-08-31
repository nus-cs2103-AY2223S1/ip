package duke;
import com.sun.javafx.application.PlatformImpl;
import javafx.application.Application;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static javafx.application.Platform.exit;


public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Label greetings;

    private static ArrayList<Task> listOfTask = new ArrayList<>();
    private final static String DIRECTORY = "./DATA";
    private final static String FILENAME = "duke.txt";
    private final static String PATHNAME = String.valueOf(Paths.get(DIRECTORY, FILENAME));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    Storage storage = new Storage(PATHNAME, listOfTask, DIRECTORY);
    Ui ui = new Ui();

        public void start(Stage stage) {
            ui.greetUi();
            storage.getData();
            scrollPane = new ScrollPane();
            dialogContainer = new VBox();
            scrollPane.setContent(dialogContainer);

            userInput = new TextField();
            sendButton = new Button("Send");
            greetings = new Label(ui.greetUi());
            greetings.setPadding(new Insets(0,7,0,7));

            AnchorPane mainLayout = new AnchorPane();
            mainLayout.getChildren().addAll(scrollPane, userInput, greetings, sendButton);

            scene = new Scene(mainLayout);

            stage.setScene(scene);
            stage.show();
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            mainLayout.setPrefSize(400.0, 600.0);

            scrollPane.setPrefSize(385, 535);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            scrollPane.setVvalue(1.0);
            scrollPane.setFitToWidth(true);

            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            userInput.setPrefWidth(325.0);

            sendButton.setPrefWidth(55.0);

            AnchorPane.setTopAnchor(scrollPane, 1.0);

            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(greetings, new ImageView(dukeImage))
            );
            AnchorPane.setBottomAnchor(sendButton, 1.0);
            AnchorPane.setRightAnchor(sendButton, 1.0);

            AnchorPane.setLeftAnchor(userInput , 1.0);
            AnchorPane.setBottomAnchor(userInput, 1.0);

            sendButton.setOnMouseClicked((event) -> {
                handleUserInput();
                userInput.clear();
            });

            userInput.setOnAction((event) -> {
                handleUserInput();
                userInput.clear();
            });
        }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        userText.setPadding(new Insets(0,7,0,7));
        Label dukeText = new Label(getResponse(userInput.getText()));
        dukeText.setPadding(new Insets(0,7,0,7));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            if (parser.isSubStringForExitCommand()) {
                ExitCommand exitCommand = new ExitCommand();
                exit();
               return exitCommand.execute(input, listOfTask, ui, storage);

            }
            Command c = parser.parse();
            return c.execute(input, listOfTask, ui, storage);
        } catch (DukeException e) {
           return e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}