package duke;

import java.util.ArrayList;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class that initialises and runs the Duke bot.
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

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * A constructor that initialises the Duke bot
     *
     * @param taskList List of tasks.
     * @param pathName The path of the text file to store and load data.
     * @throws IOException From loadUpData() method.
     */
    public Duke(TaskList taskList, String pathName) throws IOException {
        this.taskList = taskList;
        this.storage = new Storage(pathName, this.taskList);
        this.ui = new Ui();
        this.parser = new Parser(this.taskList, this.storage);

        this.storage.loadUpData();

    }

    public Duke() {

    }

    /**
     * Runs the UI of the bot.
     */
    public String runUi() {
        return this.ui.run();
    }

    /**
     * Parses the input entered by the user.
     */
    public String parse(String text) {
        return this.parser.parse(text);
    }

    /**
     * Runs the Duke bot.
     *
     * @param args Arguments passed to the main function.
     */

    /*
    public static void main(String[] args) {
        try {
            Duke dukeBot = new Duke(new TaskList(new ArrayList<>(100)), "taskList.txt");

            dukeBot.runUi();

            dukeBot.parse();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    } */

    @Override
    public void start(Stage stage) throws IOException {
        Duke dukeBot = new Duke(new TaskList(new ArrayList<>(100)), "taskList.txt");

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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(dukeBot);

        });

        userInput.setOnAction((event) -> {
            handleUserInput(dukeBot);

        });

        Label welcomeMessage = new Label(dukeBot.runUi());

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, new ImageView(duke))
        );

    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Duke dukeBot) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(dukeBot.parse(userInput.getText())));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        if (userInput.getText().equals("bye")) {
            System.exit(0);
        }

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return input;
    }

}