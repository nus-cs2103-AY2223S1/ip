package duke;

import java.io.File;

import duke.commands.Command;
import duke.exceptions.InvalidCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.DialogBox;
import duke.ui.TextUi;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Runs the GUI application.
 */
public class DukeApp extends Application {
    private static final String DATA_PATH = new File("").getAbsolutePath() + "/data/duke.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Storage storage;
    private TaskList taskList;
    private TextUi textUi;

    @Override
    public void start(Stage stage) {

        textUi = new TextUi();
        storage = new Storage(DATA_PATH);
        taskList = new TaskList(storage.load());

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button submitButton = new Button("Done");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, submitButton);

        Scene scene = new Scene(mainLayout);

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

        submitButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(submitButton, 1.0);
        AnchorPane.setRightAnchor(submitButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        submitButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();

        welcomeUser();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        boolean isExit = false;

        Label userText = new Label(userInput.getText());

        final String userCommand = userInput.getText();

        Label dukeResult;

        try {

            final Command c = Parser.parse(userCommand);
            isExit = c.isExit();

            Response response = c.execute(taskList, storage);

            dukeResult = new Label(response.getMessage());

        } catch (InvalidCommandException e) {
            textUi.showMessage(e.getMessage());
            dukeResult = new Label(e.getMessage());
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText),
                DialogBox.getDukeDialog(dukeResult)
        );
        userInput.clear();

        if (isExit) {
            System.exit(0);
        }
    }

    /**
     * Displays the welcome message to the user.
     */
    private void welcomeUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(TextUi.WELCOME_MESSAGE))
        );
    }
}
