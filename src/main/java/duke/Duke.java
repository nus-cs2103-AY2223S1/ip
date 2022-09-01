package duke;

import duke.command.Command;
import duke.exception.DukeCommandAlreadyExecutedException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
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
import javafx.scene.control.Label;

/**
 * The only public-facing class that interacts directly with the user.
 * Duke is a personal time management virtual assistant, targeted at those who prefer using CLI instead of GUI.
 */
public class Duke extends Application {

    public static final String TAB = "    ";

    private static final String DEFAULT_FILE_PATH = "saved_list.txt";
    private static final String GREETING_MESSAGE = "Hi there! I' am Duke, your personal time manager."
            + "\n" + TAB + "What can I help you?";

    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * An empty constructor that will be used to initialise Duke by Launcher
     */
    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_FILE_PATH);
        this.parser = new Parser();
    }

    /**
     * The constructor to instantiate a new Duke chatbot.
     *
     * @param filePath A relative file path that specifies where to save the list.
     */
    public Duke(String filePath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    /**
     * Starts the main logic of the chatbot.
     * It keeps listening to user input until an exit command.
     */
    public void run() {
        greet();
        startListening();
    }

    private void greet() {
        ui.printOutput(GREETING_MESSAGE);
    }

    private void startListening() {

        boolean isExit = false;

        while (!isExit) {
            String input = ui.readInput();

            Command nextCommand = parser.parse(input);
            isExit = nextCommand.isExit();

            try {
                nextCommand.execute(ui, taskList, storage);
            } catch (DukeCommandAlreadyExecutedException exception) {
                ui.printOutput(exception.getMessage());
            }
        }
    }

    // TODO JAVADOC
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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
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
     * The main logic that runs a chatbot.
     *
     * @param args String arguments for starting the logic.
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke(DEFAULT_FILE_PATH);
        chatBot.run();
    }
}
