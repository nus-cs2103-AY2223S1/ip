package drivers;

import commands.Command;
import exceptions.TumuException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Chatbot driver code. Receives the input from the user
 * and responds accordingly.
 */
public class Tumu extends Application {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/sadcat.png"));
    private Image tumu = new Image(this.getClass().getResourceAsStream("/images/bongocat.png"));

    /**
     * Constructor for the Tumu class.
     */
    public Tumu() {
        ui = new Ui();
        storage = new Storage("data/Tumu.txt");
        tasks = new TaskList(storage.loadData(ui));
    }

    /**
     * Run the greeting message.
     */
    public String runGreeting() {
        return ui.greeting();
    }

    /**
     * Program comes up with a response to the command given
     * by the user.
     */
    public String getResponse(String input) {
        String output = new String();
        String fullCommand = input;
        try {
            Command c = Parser.parse(fullCommand, ui);
            output = c.execute(tasks, ui, storage);
        } catch (TumuException e) {
            output += ui.notifyUser(e.toString());
        } catch (NumberFormatException e) {
            output += ui.notifyUser("Please (un)mark or delete a task by "
                    + "its list position (must be an integer)!");
        } catch (NullPointerException e) {
            output += ui.notifyUser("Please type a task description for the task!");
        }

        return output;
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

    private void handleUserInput() {
        String userText = userInput.getText();
        String tumuText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getTumuDialog(tumuText, tumu)
        );
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer); //attach scroll to dialog.

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Tumu");
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

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }
}
