package jenny;

import java.util.ArrayList;
import java.util.Objects;

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
import jenny.commands.Command;
import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.storage.TaskStorage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Parser;
import jenny.util.Ui;

/**
 * Initialise the JennyBot application.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public final class JennyBot extends Application {
    private final Storage<ArrayList<Task>> storage;
    private TaskList tasks;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("images/DaUser.png")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("images/DaDuke.png")));


    /**
     * Constructor for a JennyBot application.
     * Will initialise a storage at the default location under the specified name.
     */
    public JennyBot() {
        // @param filePath the name of the storage file.
        String fileName = "tasks.txt";
        ui = new Ui(System.in, System.out);
        storage = new TaskStorage<>(fileName);
        try {
            tasks = new TaskList(storage.load());
        } catch (JennyException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Run the JennyBot Application
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String nextLine = ui.read();
                Command cmd = Parser.parse(nextLine);
                cmd.run(tasks, ui, storage); // throws JennyException
                isExit = cmd.isExit();
            } catch (JennyException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new JennyBot().run();
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws Exception if something goes wrong
     */
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

        // more code to be added here later
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
        //Part 3. Add functionality to handle user input.
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
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
