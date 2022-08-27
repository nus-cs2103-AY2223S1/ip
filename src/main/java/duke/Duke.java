package duke;

import duke.gui.DialogBox;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents the Duke application.
 *
 * @author njxue
 * @version v0.1
 */
public class Duke extends Application {
    /** Ui object which prints the contents in the duke dialog. */
    private final Ui ui;

    /** Storage object which loads and saves the list of tasks. */
    private final Storage storage;

    /** TaskList object containing user's list of tasks. */
    private TaskList tasks;

    /** Display image representing the user. */
    private Image userDisplayPicture =  new Image(this.getClass().getResourceAsStream("/images/cheems.jpg"));

    /** Display image representing the duke chat-bot */
    private Image dukeDisplayPicture =  new Image(this.getClass().getResourceAsStream("/images/swole.png"));

    /** Pane representing the scrollable section of the application window. */
    private ScrollPane scrollPane;

    /** Container containing the dialogs from the user and the duke chat-bot. */
    private VBox dialogContainer;

    /** Input field for the user. */
    private TextField userInput;

    /** Send button which submits the user's input. */
    private Button sendButton;

    /**
     * Creates a new Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("storage/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException fnfe) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        Label greetLabel = new Label(ui.greetingMessage());
        DialogBox greetDialog = DialogBox.getDukeDialog(greetLabel, new ImageView(dukeDisplayPicture));

        dialogContainer.getChildren().addAll(greetDialog);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // styles
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

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // actions
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Returns the Label of a dialog.
     *
     * @param text Text contents of the dialog.
     * @return Label of a dialog.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Adds the appropriate dialogs to the UI after parsing a user's input.
     */
    private void handleUserInput() {
        Label userText = getDialogLabel(userInput.getText());
        Label dukeText = getDialogLabel(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userDisplayPicture)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeDisplayPicture))
        );
        userInput.clear();
    }

    /**
     * Parses the user's input and returns the corresponding response from the duke chat-bot.
     *
     * @param input Full user input.
     * @return Response from the duke chat-bot.
     */
    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException de) {
            return de.getMessage();
        }
    }
}
