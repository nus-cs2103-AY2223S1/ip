/*
package duke;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<Task> listOfTask = new ArrayList<>();
    private final static String DIRECTORY = "./DATA";
    private final static String FILENAME = "duke.txt";
    private final static String PATHNAME = String.valueOf(Paths.get(DIRECTORY, FILENAME));

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greetUi();
        Storage storage = new Storage(PATHNAME, listOfTask, DIRECTORY);
        storage.getData();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            try {
                String fullCommand = input.nextLine();
                Parser parser = new Parser(fullCommand);
                if (parser.isSubStringForExitCommand()) {
                    ExitCommand exitCommand = new ExitCommand();
                    exitCommand.execute(fullCommand,listOfTask,ui,storage);
                    break;
                }
                Command c = parser.parse();
                c.execute(fullCommand, listOfTask, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}*/


package duke;
import javafx.application.Application;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;




public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private static ArrayList<Task> listOfTask = new ArrayList<>();
    private final static String DIRECTORY = "./DATA";
    private final static String FILENAME = "duke.txt";
    private final static String PATHNAME = String.valueOf(Paths.get(DIRECTORY, FILENAME));
 //   private Image userImage = new Image("https://se-education.org/guides/tutorials/images/javafx/DaUser.png");
   // private Image dukeImage = new Image("https://se-education.org/guides/tutorials/images/javafx/DaDuke.png");
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    Storage storage = new Storage(PATHNAME, listOfTask, DIRECTORY);
    Ui ui = new Ui();

        public void start(Stage stage) {
            // where to put greetings...
            ui.greetUi();
            storage.getData();
            scrollPane = new ScrollPane();
            dialogContainer = new VBox();
            scrollPane.setContent(dialogContainer);

            userInput = new TextField();
            sendButton = new Button("Send");

            AnchorPane mainLayout = new AnchorPane();
            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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

            // You will need to import `javafx.scene.layout.Region` for this.
            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            userInput.setPrefWidth(325.0);

            sendButton.setPrefWidth(55.0);

            AnchorPane.setTopAnchor(scrollPane, 1.0);

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
        Label dukeText = new Label(getResponse(userInput.getText()));
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