package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Objects;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;

/**
 * The self-named main class that controls our lovely chatbot, Duke
 *
 * @author eugeneleong
 * @version 1.0
 */
public class Duke {

    //private final Image user = new Image(Objects.requireNonNull(
    //        this.getClass().getResourceAsStream("/images/NotSoPoliteCat.png")));
    //private final Image duke = new Image(Objects.requireNonNull(
    //        this.getClass().getResourceAsStream("/images/PoliteCat.png")));
    //private ScrollPane scrollPane;
    //private VBox dialogContainer;
    //private TextField userInput;
    //private Button sendButton;
    //private Scene scene;
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    /**
     * Loads Duke up with the appropriate list of tasks
     * @throws FileNotFoundException if the filePath is typed incorrectly
     */
    public Duke() throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage("./src/duke.txt");
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException de) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /*
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.getRoot().setStyle("-fx-font-family: 'serif'"); // Additional line to make code work with Launcher.java
        stage.setScene(scene);
        stage.show();
        // Step 2. Formatting the window to look as expected
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
*/
    /*
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }*/

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String fullCommand) {
        String output;
        try {
            String commandType = Parser.getCommandType(fullCommand);
            output = Parser.process(fullCommand, commandType, tasks, ui);
            // no need save when saying bye to Duke or listing
            if (!fullCommand.equals("bye") && !fullCommand.equals("list")) {
                storage.saveToFile(fullCommand);
            }
        } catch (IllegalArgumentException iae) {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } catch (IOException e) {
            return "Your input cannot be saved. Sorry! :(";
        }
        return output;
    }
}

