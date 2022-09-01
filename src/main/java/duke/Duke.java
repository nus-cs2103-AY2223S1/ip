package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
Main class that starts the running of Duke Chat Bot
*/
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ArrayList<Task> list;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke to initialise objects
     *
     * @param filePath path of txt file storing past tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            list = tasks.getTaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            list = tasks.getTaskList();
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
        }
    }

    public Duke() {

    }

    /**
     * Contains Scanner which gets user input and passes it into
     * other objects for interpretation
     */
    public void run() {
        ui.sayHello();

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "0";

        while (true) {

            userInput = myObj.nextLine();  // Read user input
            String command = Parser.parseUserInput(userInput);

            switch (command) {
                case "bye":
                    ui.showByeMessage();
                    storage.writeItems(list);
                    System.exit(0);
                case "list":
                    ui.showList(this.list);
                    break;
                case "mark":
                    String markMessage = this.tasks.markDone(Parser.getTaskName(userInput));
                    ui.showMessage(markMessage);
                    break;
                case "unmark":
                    String unmarkMessage = this.tasks.unmarkDone(Parser.getTaskName(userInput));
                    ui.showMessage(unmarkMessage);
                    break;
                case "delete":
                    try {
                        this.tasks.deleteTask(Parser.getTaskName(userInput));
                    } catch (DukeException e) {
                        ui.showMessage(e.getMessage());
                    }
                case "schedule":
                    String date = Parser.getDate(userInput);
                    ui.showSchedule(date, list);
                    break;
                case "find":
                    String keyword = Parser.getTaskName(userInput);
                    ArrayList<Task> foundTasks = this.tasks.find(keyword);
                    //ui.showMessage("Here are the matching tasks in your list:\n");
                    //ui.showList(foundTasks);
                    ui.showFoundTasks(foundTasks, keyword);
                case "todo":
                case "deadline":
                case "event":
                    try {
                        this.tasks.addTask(userInput);
                    } catch (DukeException e) {
                        ui.showMessage(e.getMessage());
                    }
                    break;
                default:
                    ui.showDoNotKnowMessage();
                    break;
            }
        }
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setSpacing(20.0);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Dukie");
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
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}