package duke;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
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
 * This class encapsulates the Duke chatbot.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class Duke extends Application {
    private static ArrayList<Task> inputs = new ArrayList<>();
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for a duke.Duke instance.
     *
     * @param filePath the path to the file for data storage
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui(this);
        this.taskList = new TaskList(this.storage.getTasks(), this.ui, this.storage);
        this.ui.updateTaskList(this.taskList);
    }

    /**
     * Overloaded constructor with no arguments.
     */
    public Duke() {
        this(System.getProperty("user.home") + "/duke.txt");
    }

    /**
     * Starts up the JavaFX GUI.
     *
     * @param stage The starting stage
     */
    @Override
    public void start(Stage stage) {
        // Initialising up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);

        // Setting up required components
        this.setUpMainLayout(mainLayout, scrollPane, userInput, sendButton);
        this.setUpStage(stage);
        this.setUpScrollPane(scrollPane, dialogContainer);
        this.setUpDialogContainer(dialogContainer);
        this.setUpIo(userInput, sendButton);

        // Show an automatic greeting message
        this.showGreeting();

        // Display the GUI
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    private void setUpScrollPane(ScrollPane scrollPane, VBox dialogContainer) {
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
    }

    private void setUpStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void setUpMainLayout(AnchorPane mainLayout,
                                 ScrollPane scrollPane,
                                 TextField userInput,
                                 Button sendButton) {
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);
    }

    private void setUpDialogContainer(VBox dialogContainer) {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((
                observable) -> scrollPane.setVvalue(1.0));

    }

    private void setUpIo(TextField userInput, Button sendButton) {
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Displays the greeting message on the GUI.
     */
    private void showGreeting() {
        Label greeting = new Label(this.ui.getGreeting());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting,
                                        new ImageView(duke))
        );
    }

    /**
     * Displays and processes the user's input.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );

        try {
            if (this.ui.handleInput(userInput.getText())) {
                // exit
                Platform.exit();
            }
        } catch (DukeException e) {
            this.updateResponse(e.toString());
        } finally {
            userInput.clear();
        }
    }

    /**
     * Displays a Dialog Box representing Duke's response.
     *
     * @param responses the responses to be displayed
     */
    public void updateResponse(String... responses) {
        for (String response : responses) {
            Label dukeTxt = new Label(response);
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(dukeTxt, new ImageView(duke))
            );
        }
    }
}
