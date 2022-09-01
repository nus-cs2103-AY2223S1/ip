package duke;

import java.util.Scanner;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
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
 * Duke is main driver for storing and displaying Tasks
 */
public class Duke extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/userAmongUsColoured.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeAmongUsColoured.png"));

    private static final String SAVE_DIRECTORY_PATH = "../data";
    private static final String SAVE_FILE_PATH = "../data/duke.txt";
    private static TaskList tasklist = new TaskList();
    private static Storage storage = new Storage(SAVE_DIRECTORY_PATH, SAVE_FILE_PATH);
    private static Ui ui = new Ui();

    private static String dukeReply (String inputString) {
        String[] inputs = Parser.splitBySpace(inputString);
        String input = inputs[0];
        String reply = "";
        try {
            if (input.equals("bye")) {
                reply = ui.showBye();
            } else if (input.equals("list")) {
                reply = ui.showList(tasklist);
            } else if (input.equals("mark")) {
                reply = ui.showMark(tasklist, inputs[1]);
                storage.writeToFile(tasklist.listOfTasksForSaving());
            } else if (input.equals("unmark")) {
                reply = ui.showUnmark(tasklist, inputs[1]);
                storage.writeToFile(tasklist.listOfTasksForSaving());
            } else if (input.equals("todo")) {
                if (inputs.length <= 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                tasklist.appendToDo(inputString);
                reply = ui.showAddedTask(tasklist);
                storage.writeToFile(tasklist.listOfTasksForSaving());
            } else if (input.equals("deadline")) {
                if (inputs.length <= 1) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] deadlineDescription = Parser.splitBySlash(inputString);
                tasklist.appendDeadline(deadlineDescription[0], deadlineDescription[1]);
                reply = ui.showAddedTask(tasklist);
                storage.writeToFile(tasklist.listOfTasksForSaving());
            } else if (input.equals("event")) {
                if (inputs.length <= 1) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                String[] eventDescription = Parser.splitBySlash(inputString);
                tasklist.appendEvent(eventDescription[0], eventDescription[1]);
                reply = ui.showAddedTask(tasklist);
                storage.writeToFile(tasklist.listOfTasksForSaving());
            } else if (input.equals("delete")) {
                String taskMessage = tasklist.removeTask(inputs[1]);
                reply = ui.showDeletedTask(taskMessage, tasklist);
                storage.writeToFile(tasklist.listOfTasksForSaving());
            } else if (input.equals("find")) {
                reply = ui.showMatch(tasklist, inputString.replace("find ", ""));
            } else {
                throw new DukeException("Sorry, I don't recognise the input :( Please try again ");
            }
        } catch (DukeException e) {
            reply = ui.showError(e.getMessage());
        } catch (IOException e) {
            reply = ui.showError(e.getMessage());
        }
        return reply;
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
        return dukeReply(input);
    }

    @Override
    public void start(Stage stage) {

        try {
            storage.checkExistsOrCreateNewFile(tasklist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

        //Set spacing for dialogContainer
        dialogContainer.setSpacing(10);
        dialogContainer.setPadding(new Insets(10,5,10,5));

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

        // Start by greeting the user
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label(ui.showGreeting()), new ImageView(duke)));
    }


//    /**
//     * Executes run method upon starting
//     * @param args optional arguments
//     */
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        Scanner scanner = new Scanner(System.in);
//        run(scanner);
//    }
}
