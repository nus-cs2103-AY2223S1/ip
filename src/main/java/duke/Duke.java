package duke;

import java.util.Objects;

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

import duke.commands.Command;

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
    private final Image userPhoto = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/randomJonin.png")));
    private final Image narutoPhoto = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/naruto.png")));


    public Duke() {
        this.ui = new UI();
        this.taskList = new TaskList(ui);
        this.parser = new Parser();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Set up Dialogue Box
        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Set up Scroll Pane
        scrollPane = new ScrollPane();

        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(400, 550);
        scrollPane.setMinSize(200, 250);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Set User Input
        userInput = new TextField();
        userInput.setPrefWidth(325.0);
        userInput.setMinWidth(160);

        // Set Send Button
        sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        sendButton.setMinWidth(40);

        // Set Anchor Pane
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        mainLayout.setPrefSize(400.0, 600.0);

        scene = new Scene(mainLayout);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> this.handleUserInput());

        userInput.setOnAction((event) -> this.handleUserInput());

        // Handle Close Button
        stage.setOnCloseRequest(e -> {
            this.taskList.destructor();
            stage.close();
        });

        // Setting Stage
        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(300.0);
        stage.setMinWidth(200.0);
        stage.setMaxHeight(620);
        stage.setMaxWidth(415);

        stage.setScene(scene);
        stage.show();


//        ui.printWelcomeMessage();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
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
            taskList.destructor();
            this.stage.close();
            return ui.getGoodbyeMessage();
        case HELP:
            return ui.getHelpMessage();
        case ERROR:
            return command.getArguments()[0];
        default:
            return taskList.executeTask(command);
        }
    }

    /**
     * Run the chatbot.
     * @param args This is useless.
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
