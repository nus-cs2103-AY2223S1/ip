package bobthebot.gui;

import java.util.Timer;
import java.util.TimerTask;

import bobthebot.command.ReminderCommand;
import bobthebot.exceptions.BobException;
import bobthebot.tasks.ToDoList;
import bobthebot.utils.Parser;
import bobthebot.utils.Storage;
import bobthebot.utils.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;
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

public class Main extends Application {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private static final String FILEPATH = "./../../data/data.txt";
    private static final String TITLE = "BobTheBot - Friendly Task Managing Bot";
    private static final int STAGE_MIN_HEIGHT = 700;
    private static final int STAGE_MAX_HEIGHT = 500;
    private static final int MAIN_LAYOUT_PREF_WIDTH = 500;
    private static final int MAIN_LAYOUT_PREF_HEIGHT = 700;
    private static final int SCROLLPANE_PREF_WIDTH = 500;
    private static final int SCROLLPANE_PREF_HEIGHT = 675;
    private static final int USER_INPUT_WIDTH = 440;
    private static final int SEND_BUTTON_WIDTH = 65;

    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bob = new Image(this.getClass().getResourceAsStream("/images/bob.png"));

    private Parser parser = new Parser();
    private ToDoList todolist;
    private Storage storage = new Storage(FILEPATH);

    @Override
    public void start(Stage stage) {
        initialise();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle(TITLE);
        stage.setResizable(true);
        stage.setMinHeight(STAGE_MIN_HEIGHT);
        stage.setMinWidth(STAGE_MAX_HEIGHT);

        mainLayout.setPrefSize(MAIN_LAYOUT_PREF_WIDTH, MAIN_LAYOUT_PREF_HEIGHT);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);

        this.todolist = new ToDoList(this.storage.load(), storage);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initialises the components of the GUI.
     */
    private void initialise() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        scrollPane.setPrefSize(SCROLLPANE_PREF_WIDTH, SCROLLPANE_PREF_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(getDialogLabel(
                Ui.sayWelcome()), new ImageView(bob)));

        userInput.setPrefWidth(USER_INPUT_WIDTH);
        sendButton.setPrefWidth(SEND_BUTTON_WIDTH);
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label bobText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(bobText, new ImageView(bob))
        );
        userInput.clear();
    }

    /**
     * Gets Bob's response to the input.
     */
    private String getResponse(String input) {
        if (input.equals("bye")) {
            quit();

            ReminderCommand reminderCommand = new ReminderCommand(todolist);
            String response = reminderCommand.execute() + "\n";
            response += Ui.sayGoodbye(todolist);

            return response;
        }

        String response = null;
        try {
            response = parser.parseCommand(input, this.todolist);
        } catch (BobException exception) {
            response = exception.getMessage();
        } finally {
            return response;
        }
    }


    /**
     * Quits the program.
     */
    private void quit() {
        TimerTask exitApp = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(exitApp, 2000);
    }
}
