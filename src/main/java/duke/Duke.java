package duke;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 *
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/sman.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/dog.png"));


    private final Ui ui = new Ui();
    private TaskList taskList = null;
    /**
     * The file path at where the tasks are stored
     */
    public static String filePath = "tasks.txt";

    public Duke() {}

        /**
         * Constructs an instance with the file path
         * @param filePath The path to the file
         */
    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
//        try {
            taskList = new TaskList(storage);
//        } catch (CustomMessageException e) {
//            ui.showLoadingError();
//        }
    }

    /**
     * The initialisation code that starts the chatbot
     */
    public void run() {
        ui.showWelcome();
        Parser parser = new Parser(taskList);
        while (true) {
            try {
                String nextCommand = ui.getNextCommand();
                ui.showLine();
                System.out.print(parser.parseUserCommand(nextCommand, parser.breakLoopIndicator));
                if (parser.breakLoopIndicator.getIsExitCommand()) {
                    ui.showExitMessage();
                    break;
                }
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
            } catch (CustomMessageException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

//      Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//      Scene scene = new Scene(new StackPane(l), 640, 480);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        // set up the display container
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // format the window
        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        // to enable auto-scroll
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.getText(), user),
                DialogBox.getDukeDialog(dukeText.getText(), duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    // made this public so that it can be used in MainWindow
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Tne driver method
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        new Duke(Duke.filePath).run();
    }
}
