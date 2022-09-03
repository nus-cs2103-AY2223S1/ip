package duke;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import java.util.Scanner;

/**
 * A Personal Assistant Chatbot that helps a person keep track of several things like tasks.
 *
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/jim.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dwight.jpg"));

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


        scene = new Scene(mainLayout);

        // Formatting the window
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

        AnchorPane.setTopAnchor(scrollPane,1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        // For when user presses enter
        userInput.setOnAction((event) -> {
           handleUserInput();
        });

        // For when dialog container gets longer
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    public Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Updates the dialog box
     */
    public void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * Gets a response from the Duke bot
     *
     * @param input
     * @return response String
     */
    public String getResponse(String input) {
        return this.parser.parse(input, this.ui, this.taskList, this.storage);
    }

    /**
     * The constructor of the Duke class.
     *
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        this.taskList = new TaskList();
        this.storage.load(this.taskList);
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Main method which instantiates the Duke chatbot and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {


    }
}
