package duke;

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

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The main application.
 */
public class Duke extends Application {

    public final String FILE_NAME;
    private final Storage FO;
    private final TaskList TASK_LIST;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Returns a Duke instance.
     *
     * @param fileName The name of the file to store the tasks.
     * @param fo       The FileOperator to perform file manipulation.
     * @param taskList The TaskList instance to manage all tasks.
     */
    public Duke(String fileName, Storage fo, TaskList taskList) {
        FILE_NAME = fileName;
        this.FO = fo;
        this.TASK_LIST = taskList;
    }

    /**
     * A default constructor for Duke with the default values of its attributes.
     */
    public Duke() {
        FILE_NAME = "data.txt";
        FO = new Storage("data.txt");
        TASK_LIST = new TaskList();
    }

    /**
     * Serves as the entry point for the main Duke application.
     *
     * @param args The user input to the application.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data.txt", new Storage("data.txt"), new TaskList());
        Ui ui = new Ui(duke.FILE_NAME);
        ui.printWelcomeMessage();

        // Read from file and load into tasks
        File file;

        try {
            file = new File(duke.FILE_NAME);
            if (file.createNewFile()) {
                ui.printCreateNewFile();
            } else {
                duke.FO.loadAllTasksFromFile(duke.TASK_LIST);
                ui.printLoadTasksFromFile();
                ui.printLineBreak();
            }
        } catch (IOException e) {
            ui.printErrorCreatingFile();
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        while (!input.equals(Commands.BYE)) {
            duke.run(input);
            input = sc.nextLine();
        }

        // cleaning up and shutting down Duke
        ui.printByeMessage();
        sc.close();
    }

    /**
     * Runs the Duke application by processing the user input and executing necessary commands.
     *
     * @param input The input entered by the user.
     */
    public void run(String input) {
        Ui ui = new Ui(FILE_NAME);
        try {
            String[] inputString = input.split(" ", 2);
            Parser parser = new Parser(inputString);
            String command = inputString[0];
            ui.printLineBreak();
            switch (command) {
            case Commands.LIST:
                TASK_LIST.listTasks();
                break;
            case Commands.MARK:
                int taskNumber = parser.parseMark();
                TASK_LIST.markAsDone(taskNumber - 1, FO); // since display is 1-indexed
                break;
            case Commands.UNMARK:
                taskNumber = parser.parseUnmark();
                TASK_LIST.markAsNotDone(taskNumber - 1, FO); // since display is 1-indexed
                break;
            case Commands.TODO:
                Todo task = parser.parseTodo();
                TASK_LIST.add(task, FO);
                break;
            case Commands.DEADLINE:
                Deadline deadline = parser.parseDeadline();
                TASK_LIST.add(deadline, FO);
                break;
            case Commands.EVENT:
                Event event = parser.parseEvent();
                TASK_LIST.add(event, FO);
                break;
            case Commands.DELETE:
                taskNumber = parser.parseDelete();
                TASK_LIST.delete(taskNumber - 1, FO); // since we store tasks 0-indexed in ArrayList
                break;
            case Commands.FIND:
                String keyword = parser.parseFind();
                TASK_LIST.find(keyword);
                break;
            default:
                ui.printCommandNotRecognized();
            }
        } catch (DukeException e) {
            System.out.printf("\t%s\n", e.getMessage());
        } catch (NumberFormatException e) {
            ui.printErrorTaskNumber();
        } catch (java.time.format.DateTimeParseException e) {
            ui.printErrorDatetimeFormat();
        } finally {
            ui.printLineBreak();
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
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

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        ImageView userImage = new ImageView(user);
//        ImageView dukeImage = new ImageView(duke);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, userImage),
//                DialogBox.getDukeDialog(dukeText, dukeImage)
//        );
//        userInput.clear();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

