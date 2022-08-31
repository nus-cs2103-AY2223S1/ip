package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Duke bot object class, which has a task list, a storage space and a user interface.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a Duke object with the given filepath.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("/data/tasks.txt");
        TaskList currentTasks;
        try {
            currentTasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            currentTasks = new TaskList();
        }
        this.tasks = currentTasks;
    }

    /**
     * Gets string of welcome message from Duke.
     *
     * @return string of welcome message from Duke.
     */
    public String showWelcomeMessage() {
        return this.ui.showWelcome();
    }

    /**
     * Main view of the duke application.
     *
     * @param stage main view of the duke application.
     */
    @Override
    public void start(Stage stage) {

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

        // Formatting the window to look as expected.
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

        //Event handlers
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException, IOException {
        String userText = userInput.getText();
        String dukeText = this.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImg),
                DialogBox.getDukeDialog(dukeText, dukeImg)
        );
        userInput.clear();
    }

    /**
     * Gets Duke response for the user's input.
     *
     * @param input of what the user types.
     * @return a string of duke's response.
     * @throws DukeException if invalid user input.
     * @throws IOException   if file not found.
     */
    public String getResponse(String input) throws IOException {
        return Parser.parse(input, tasks, ui, storage);
    }
}
