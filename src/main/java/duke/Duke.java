package duke;

import java.util.Objects;

import duke.commands.Command;
import duke.javafx.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * Chatbot main.
 */
public class Duke extends Application {
    // Chatbot stuff
    private final UI ui;
    private final TaskList taskList;
    private final Parser parser;

    // Java FX Stuff
    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;
    private final Image userPhoto =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/randomJonin.png")));
    private final Image narutoPhoto =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/naruto.png")));

    /**
     * Creates an instance of Duke.
     */
    public Duke() {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.parser = new Parser();
    }

    @Override
    public void start(Stage stage) {

        this.dialogContainer = this.setUpDialogueContainer();
        this.scrollPane = this.setUpScrollPane();
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        this.userInput = this.setUpUserInput();
        this.sendButton = this.setUpSendButton();
        this.mainLayout = this.setUpLayout(this.scrollPane, this.userInput, this.sendButton);
        this.scene = new Scene(this.mainLayout);
        this.stage = setUpStage(stage);
        this.addWelcomeMessage();

        userInput.setOnAction((event) -> this.handleUserInput());
        sendButton.setOnMouseClicked((event) -> this.handleUserInput());

        this.stage.setScene(scene);
        this.stage.show();
    }

    private ScrollPane setUpScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(400, 550);
        scrollPane.setMinSize(200, 250);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        return scrollPane;
    }

    private VBox setUpDialogueContainer() {
        VBox dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        return dialogContainer;
    }

    private TextField setUpUserInput() {
        TextField userInput = new TextField();
        userInput.setPrefWidth(325.0);
        userInput.setMinWidth(160);


        return userInput;
    }

    private Button setUpSendButton() {
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        sendButton.setMinWidth(40);


        return sendButton;
    }

    private AnchorPane setUpLayout(ScrollPane scrollPane, TextField userInput, Button sendButton) {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        mainLayout.setPrefSize(400.0, 600.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        return mainLayout;
    }

    private Stage setUpStage(Stage stage) {
        stage.setTitle("Naruto");
        stage.setResizable(true);
        stage.setMinHeight(300.0);
        stage.setMinWidth(200.0);
        stage.setMaxHeight(620);
        stage.setMaxWidth(415);
        // Handle Close Button
        stage.setOnCloseRequest(this::handleCloseRequest);

        return stage;
    }

    private void addWelcomeMessage() {
        Label welcomeMessage = new Label(this.ui.getWelcomeMessage());
        this.dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(welcomeMessage, new ImageView(this.narutoPhoto)));
    }

    private void handleUserInput() {
        Label userText = new Label(this.userInput.getText());
        this.dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(this.userPhoto)));
        Label dukeText = new Label(parseInput(userInput.getText()));
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(this.narutoPhoto)));
        userInput.clear();
    }

    private String parseInput(String rawInput) {
        Command command = parser.parse(rawInput);

        switch (command.getCommand()) {
        case UNKNOWN:
            return ui.getDefaultMessage();
        case BYE:
            handleCloseRequest(null);
            return ui.getGoodbyeMessage();
        case HELP:
            return ui.getHelpMessage();
        case ERROR:
            return command.getArguments()[0];
        default:
            return taskList.executeTask(command);
        }
    }

    private void handleCloseRequest(WindowEvent windowEvent) {
        this.taskList.destructor();
        this.stage.close();
    }
}
