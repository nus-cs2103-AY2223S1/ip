package duke;

import java.io.IOException;

import duke.backend.Storage;
import duke.backend.TaskList;
import duke.view.DialogBox;
import duke.view.Ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke is main driver for storing and displaying Tasks
 */
public class Duke extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/userAmongUsColoured.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeAmongUsColoured.png"));

    private static final String SAVE_DIRECTORY_PATH = "../data";
    private static final String SAVE_FILE_PATH = "../data/duke.txt";
    private static TaskList tasklist = new TaskList();
    private static Storage storage = new Storage(SAVE_DIRECTORY_PATH, SAVE_FILE_PATH);
    private static Ui ui = new Ui();

    private static String dukeReply (String inputString) {
        String[] inputs = Parser.splitBySpace(inputString);
        String input = inputs[0];
        String reply = "";
        try {
            if (input.equals("bye")) {
                reply = ui.showBye();
            } else if (input.equals("list")) {
                reply = ui.showList(tasklist);
            } else if (input.equals("mark")) {
                reply = ui.showMark(tasklist, inputs[1]);
            } else if (input.equals("unmark")) {
                reply = ui.showUnmark(tasklist, inputs[1]);
            } else if (input.equals("todo")) {
                checkInvalidDescription(input, inputs);
                tasklist.appendToDo(inputString);
                reply = ui.showAddedTask(tasklist);
            } else if (input.equals("deadline")) {
                checkInvalidDescription(input, inputs);
                String[] deadlineDescription = Parser.splitBySlash(inputString);
                tasklist.appendDeadline(deadlineDescription[0], deadlineDescription[1]);
                reply = ui.showAddedTask(tasklist);
            } else if (input.equals("event")) {
                checkInvalidDescription(input, inputs);
                String[] eventDescription = Parser.splitBySlash(inputString);
                tasklist.appendEvent(eventDescription[0], eventDescription[1]);
                reply = ui.showAddedTask(tasklist);
            } else if (input.equals("delete")) {
                String taskMessage = tasklist.removeTask(inputs[1]);
                reply = ui.showDeletedTask(taskMessage, tasklist);
            } else if (input.equals("find")) {
                reply = ui.showMatch(tasklist, inputString.replace("find ", ""));
            } else if (input.equals("remind")) {
                reply = ui.showReminder(tasklist);
            } else {
                throw new DukeException("Sorry, I don't recognise the input :( Please try again ");
            }
            saveData(storage, tasklist);
        } catch (Exception e) {
            reply = ui.showError(e.getMessage());
        }
        return reply;
    }

    private static void saveData(Storage storage, TaskList tasklist) throws DukeException{
        String contentToSave = tasklist.listOfTasksForSaving();
        try {
            storage.writeToFile(contentToSave);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static void checkInvalidDescription(String taskType, String[] inputs) throws DukeException {
        if (inputs.length <= 1) {
            throw new DukeException(String.format("The description of %s cannot be empty", taskType));
        }
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Generate a reply by Duke for the given input by User
     * @param input user's input to the GUI
     * @return String that contains the reply by Duke based on the given input
     */
    private String getResponse(String input) {
        return dukeReply(input);
    }

    private void createOrLoadDataStorage() {
        try {
            storage.checkExistsOrCreateNewFile(tasklist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Text configureText() {
        Text text = new Text();
        text.setText("AMONG US EDITION");
        text.setWrappingWidth(380);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(new Font(20).font("Courier New", 20));
        text.setFill(Color.WHITE);
        return text;
    }

    private ScrollPane configureScrollPane() {
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(385, 500);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    private Button configureSendButton() {
        sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        sendButton.setBackground(new Background(
                        new BackgroundFill(Color.PINK,
                                new CornerRadii(5),
                                new Insets(0))
                )
        );
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        return sendButton;
    }

    private TextField configureUserInput() {
        userInput = new TextField();
        userInput.setPrefWidth(325.0);
        userInput.setBackground(new Background(
                        new BackgroundFill(Color.PALEGOLDENROD,
                                new CornerRadii(5),
                                new Insets(0))
                )
        );
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        return userInput;
    }

    private VBox configureDialogContainer() {
        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(10);
        return dialogContainer;
    }

    private AnchorPane configureAnchorPane(ScrollPane scrollPane, TextField userInput, Button sendButton, Text text) {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, text);
        mainLayout.setBackground(new Background(
                                new BackgroundFill(Color.DIMGREY,
                                CornerRadii.EMPTY,
                                new Insets(0))
                )
        );
        return mainLayout;
    }

    private void configureSceneAndStage(Stage stage, AnchorPane mainLayout) {
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Duke ChatBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();
    }

    private void configureAnchorPaneLayout(ScrollPane scrollPane, Button sendButton, TextField userInput, Text text) {
        AnchorPane.setTopAnchor(text, 7.0);
        AnchorPane.setTopAnchor(scrollPane, 35.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    @Override
    public void start(Stage stage) {

        createOrLoadDataStorage();

        scrollPane = configureScrollPane();
        dialogContainer = configureDialogContainer();
        userInput = configureUserInput();
        sendButton = configureSendButton();
        Text text = configureText();

        scrollPane.setContent(dialogContainer);

        AnchorPane mainLayout = configureAnchorPane(scrollPane, userInput, sendButton, text);

        configureSceneAndStage(stage, mainLayout);

        mainLayout.setPrefSize(400.0, 600.0); // Doing this before scene requires change to sizing,
                                                                // abit time consuming

        configureAnchorPaneLayout(scrollPane, sendButton, userInput, text);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Start by greeting the user
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label(ui.showGreeting()), new ImageView(duke)));
    }

}
