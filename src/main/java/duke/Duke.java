package duke;
import java.util.Timer;
import java.util.TimerTask;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.functions.storage.Storage;
import duke.gui.DialogBox;
import duke.instruction.Instruction;
import duke.support.DukeException;
import duke.support.Parser;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//@@author leehuiyulaura-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart3.html
// with modifications
/**
 * The class of the Duke bot.
 */
public class Duke extends Application {

    private TaskList userTaskList;
    private Storage data;
    private Parser parser;
    private String filePath;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Duke class constructor that initialises a Duke bot.
     *
     * @param filePath The filepath in which this user wants to store their list of tasks in.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        this.userTaskList = new TaskList();
        this.data = new Storage(this.userTaskList, this.filePath);
        this.userTaskList = this.data.loadFile(this.userTaskList);
    }
    /**
    * Returns the specific TaskList created for this Duke instance.
     *
    * @return The specific TaskList created for this Duke instance.
    */
    public TaskList getUserTaskList() {
        return this.userTaskList;
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.printIntro(), duke));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add.
     * @return A label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
    * Iteration 2:
    * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
    * the dialog container. Clears the user input after processing.
    */
    @FXML
    private void handleUserInput() {
        this.parser = new Parser();
        String userText = userInput.getText();
        String dukeText = this.getResponse(userText, this.parser);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
    * Parses the user's response to return Duke's respective response in the GUI.
    *
    * @param input The user's input.
    * @param parser The parser that will be used to parse the user's input.
    * @return Duke's response to the input given by the user.
    */
    public String getResponse(String input, Parser parser) {
        Instruction instruction = parser.userInput(input);
        String message;
        try {
            message = instruction.execute();
        } catch (DukeException exception) {
            message = exception.getMessage();
        }
        return message;
    }

    /**
     * Terminates Duke Programme when user inputs "bye".
     */
    public void exitDuke() {
        data.saveFile(this.userTaskList, this.filePath);
        TimerTask exitDuke = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(exitDuke, 1000);
    }

}
//@@author


