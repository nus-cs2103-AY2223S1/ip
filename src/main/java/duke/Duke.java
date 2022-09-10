package duke;

import java.nio.file.Paths;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class of the Duke chat-bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /*@Override
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
    }*/

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    /*private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }*/

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
        );
        userInput.clear();
    }*/

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    /*protected String getResponse(String input) {
        return "Duke heard: " + input;
    }*/

    /**
     * Constructor for the Duke class.
     *
     * @param filePath Provides the file location for loading and storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Default constructor for the Duke class.
     */
    public Duke() {
    }

    /**
     * Starts the Duke chat-bot.
     */
    /*public void run() {
        Parser parser = new Parser();
        this.ui.start();
        for (Task t : this.tasks.getTasks()) {
            System.out.println("     " + t.toString());
        }
        while (!(ui.getResponse().equals("bye"))) {
            ui.askForInput();
            parser.parse(ui.getResponse(), this.tasks);
        }
        storage.save(tasks);
        System.out.println("     Sad to see you go! Visit me again soon!");
    }*/

    /**
     * Shows a welcome message in the chat.
     *
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    public void sendWelcomeMessage(VBox dialogContainer, Image dukeImage) {
        //this.ui.start(dialogContainer, dukeImage);
        String greetings = "Good day to you! I'm Bob!\n"
                + "I will help you to keep track of your tasks!\n"
                + "The following are your saved tasks:";
        for (Task t : this.tasks.getTasks()) {
            greetings = greetings + "\n       " + t.toString();
        }
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetings, dukeImage));
    }

    /**
     * Parses the user input and shows a message with a suitable response in the chat.
     *
     * @param input The user input.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param userImage The image of the user.
     * @param dukeImage The image of Duke.
     */
    public void handleUserInput(String input, VBox dialogContainer, Image userImage, Image dukeImage) {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));

        Parser parser = new Parser();
        if (!(input.equals("bye"))) {
            try {
                parser.parse(input, this.tasks, dialogContainer, dukeImage);
            } catch (DukeException e) {
                dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(e.getMessage(), dukeImage));
            }
        } else {
            storage.save(tasks);
            String byeMessage = "Sad to see you go! Visit me again soon!";
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(byeMessage, dukeImage));
        }
    }

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        Path pathToDuke = Paths.get(workingDir, "data", "duke.txt");
        new Duke(String.valueOf(pathToDuke));
    }
}
