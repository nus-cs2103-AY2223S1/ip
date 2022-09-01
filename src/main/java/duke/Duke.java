package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.CompileException;
import duke.exception.DukeException;
import duke.exception.DukeRuntimeException;
import duke.gui.DialogBox;
import duke.gui.GUI;
import duke.util.MessagePrinter;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
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



/**
 * The Duke.
 */
public class Duke extends Application {
    private boolean isTerminated;
    private MessagePrinter messagePrinter;
    private TaskList tasks;
    private Storage storage;
    private GUI gui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * The constructor of the Duke with given storage path.
     *
     * @param storagePath The given storage path.
     */
    public Duke(String storagePath) {
        initialize(storagePath);
    }

    /**
     * The constructor of the Duke with given storage path.
     */
    public Duke() {
        String storagePathWin = "data\\duke.txt";
        String storagePathElse = "data/duke.txt";
        String storagePath = System.getProperty("os.name").startsWith("Windows") ? storagePathWin : storagePathElse;
        initialize(storagePath);
    }

    /**
     * The main method of Duke.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        String storagePathWin = "data\\duke.txt";
        String storagePathElse = "data/duke.txt";
        String storagePath = System.getProperty("os.name").startsWith("Windows") ? storagePathWin : storagePathElse;
        new Duke(storagePath).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private void initialize(String storagePath) {
        this.tasks = new TaskList();
        this.messagePrinter = new MessagePrinter();
        this.storage = new Storage(storagePath);
        this.execute(Command.greet());
        this.gui = new GUI();
    }

    /**
     * The method to launch Duke.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!this.isTerminated) {
            try {
                if (scanner.hasNext()) {
                    String entry = scanner.nextLine();
                    Command command = parse(entry);
                    execute(command);
                    this.isTerminated = command.isTerminated();
                }
            } catch (DukeException dukeException) {
                handle(dukeException);
            }
        }
    }

    private Command parse(String entry) throws CompileException {
        return Parser.parseCommand(entry);
    }

    /**
     * The method to execute a givenCommand.
     *
     * @param command The given Command.
     */
    public String execute(Command command) throws DukeRuntimeException {
        return command.execute(this.tasks, this.messagePrinter, this.storage);
    }

    /**
     * The method to print given DukeException Details.
     *
     * @param dukeException The given DukeException.
     */
    public String handle(DukeException dukeException) {
        return this.messagePrinter.printMessage(dukeException.getMessage());
    }

    /**
     * Returns the response of Duke with given input.
     *
     * @param input The given input.
     * @return The response of Duke
     */
    public String getResponse(String input) {
        try {
            Scanner scanner = new Scanner(input);
            if (scanner.hasNext()) {
                String entry = scanner.nextLine();
                Command command = parse(entry);
                this.isTerminated = command.isTerminated();
                return execute(command);
            }
        } catch (DukeException dukeException) {
            return handle(dukeException);
        }
        return null;
    }
}
