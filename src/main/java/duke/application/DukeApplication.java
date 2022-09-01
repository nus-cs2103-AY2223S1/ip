package duke.application;

import duke.commands.*;
import duke.exceptions.EmptyBodyException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnableToSaveException;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.NoSuchElementException;
import java.util.Optional;

public class DukeApplication extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private StorageFile storageFile;
    private TaskList tasks;
    private Ui ui;
    /**
     * Sets up required objects, loads the data from the storage file.
     * Prints welcome message.
     */
    public DukeApplication() {
        ui = new Ui();
        storageFile = new StorageFile();
        tasks = new TaskList(storageFile.loadTasks());
    }

    /** Runs the program until termination */
    private void run() {
        ui.showWelcomeMessage();
        runProgram();
        exit();
    }

    /** Runs the program loop, ends if an exit command is inputted */
    private void runProgram() {
        boolean hasEnded = false;
        while (!hasEnded) {
            try {
                final String userCommand = ui.getUserCommand();
                final Command command = new Parser().parseCommand(userCommand);
                command.execute(tasks, ui, storageFile);
                hasEnded = command.hasEnded();
            } catch (UnableToSaveException | InvalidInputException e) {
                ui.showMessages(e.getMessage());
            }
        }
    }

    /** Exits the program on call */
    private void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new DukeApplication().run();
    }

    /*------------------------------------------------------------------*/

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

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
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
    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);

        String dukeResponse = getResponse(input);
        Label response = new Label(dukeResponse);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(response, new ImageView(duke))
        );

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            if ((input.equals(TodoCommand.COMMAND_WORD.toLowerCase()))
            || (input.equals(MarkCommand.COMMAND_WORD.toLowerCase()))
            || (input.equals(DeleteCommand.COMMAND_WORD.toLowerCase()))
            || (input.equals(FindCommand.COMMAND_WORD.toLowerCase()))) {

                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setTitle("Second input");
                dialog.setContentText("Your task description: ");
                Optional<String> result = dialog.showAndWait();

                if (result.isEmpty()) {
                    throw new EmptyBodyException();
                }

                String secondInput = result.get();
                final Command command = new Parser().parseTwoArgsCommand(input, secondInput);
                return command.execute(tasks, ui, storageFile);

            } else if ((input.equals(DeadlineCommand.COMMAND_WORD.toLowerCase()))
                    || (input.equals(EventCommand.COMMAND_WORD.toLowerCase()))) {

                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                dialog.setTitle("Second input");
                dialog.setContentText("Your task description: ");
                Optional<String> result = dialog.showAndWait();

                if (result.isEmpty()) {
                    throw new EmptyBodyException();
                }
                String secondInput = result.get();

                TextInputDialog secondDialog = new TextInputDialog();
                secondDialog.setHeaderText(null);
                secondDialog.setGraphic(null);
                secondDialog.setTitle("Third input");
                secondDialog.setContentText("When (YYYY-MM-DD): ");
                Optional<String> thirdResult = secondDialog.showAndWait();
                if (thirdResult.isEmpty()) {
                    throw new EmptyBodyException();
                }
                String thirdInput = thirdResult.get();
                final Command command = new Parser().parseThreeArgsCommand(input, secondInput, thirdInput);
                return command.execute(tasks, ui, storageFile);
            } else {
                final Command command = new Parser().parseCommand(input);
                return command.execute(tasks, ui, storageFile);
            }
        } catch (InvalidInputException | UnableToSaveException | EmptyBodyException e) {
            return e.getMessage();
        }

    }
}
