package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
//import javafx.scene.control.Label;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
//import javafx.scene.layout.Region;
//import javafx.scene.shape.Circle;
//import javafx.scene.text.Font;
import javafx.stage.Stage;

import exceptions.DukeException;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.util.Scanner;

/**
 * A basic CLI application for managing tasks.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Enum for task type.
     */
    public enum TaskType {
        EVENT, DEADLINE, TODO
    }

    /**
     * The TaskList instance which manages the user's tasks.
     */
    private TaskList taskList;

    /**
     * The Storage instance which allows for the caching of
     * a user's tasks between sessions.
     */
    private Storage storage;

    /**
     * Constructor for a Duke instance.
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.displayErrorMessage(e);
            this.taskList = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Create a new Label control
//        helloWorld.setFont(new Font("Arial", 50));
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        // stage.setScene(scene); // Setting the stage to show our screen
//        // stage.show(); // Render the stage
//
//        // Tutorial: Building chat UI
//        this.scrollPane = new ScrollPane();
//        this.dialogContainer = new VBox();
//        this.scrollPane.setContent(dialogContainer); // attach scroll pane to vertical box
//
//        this.userInput = new TextField();
//        this.sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);
//
//        Scene chatUi = new Scene(mainLayout);
//        stage.setScene(chatUi);
//        stage.show();
//
//        // Tutorial: Add styling
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(385, 535);
//        this.scrollPane.setPrefSize(385, 535);
//        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        this.scrollPane.setVvalue(1.0);
//        this.scrollPane.setFitToWidth(true);
//
//        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        this.userInput.setPrefWidth(325.0);
//        this.sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0); // like margin??
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // Tutorial: Add logic to handle user input (just echoing...)
//        this.sendButton.setOnMouseClicked((event) -> {
//            this.dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        this.userInput.setOnAction((event) -> { // seems a bit permissive
//            this.dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        // Somehow any v value works... but this is to enable auto-scroll (kinda)
//        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        // Tutorial: Add functionality to handle user input
//        this.sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        this.userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//        return textToAdd;
//    }

//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        ImageView userImage = new ImageView(this.user);
//        ImageView dukeImage = new ImageView(this.duke);
//
//        Circle userCircle = new Circle(userImage.getX() + 50, userImage.getY() + 50, 50);
//        Circle dukeCircle = new Circle(dukeImage.getX() + 50, dukeImage.getY() + 50, 50);
//
//        userImage.setClip(userCircle);
//        dukeImage.setClip(dukeCircle);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, userImage),
//                DialogBox.getDukeDialog(dukeText, dukeImage)
//        );
//        userInput.clear();
//    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Starts the application.
     * @param args Optional command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Parses the user's input and decides the appropriate method to
     * call based on the internal decision tree implemented in Parser.
     *
     * Terminates if the user enters 'bye'.
     */
    public void run() {
        Ui.welcomeUser();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            if (s.equals("bye")) {
                Ui.sayGoodbye();
                break;
            } else if (s.equals("list")) {
                this.taskList.listTasks();
            } else {
                try {
                    Parser.decide(s, arr, this.taskList, this.storage);
                } catch (DukeException e) {
                    Ui.displayErrorMessage(e);
                }
            }
        }
    }
}
