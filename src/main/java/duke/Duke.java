package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
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

import duke.exception.ContentNotFoundException;
import duke.exception.DateNotFoundException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.tools.CommandParser;
import duke.tools.Storage;
import duke.tools.Ui;

/**
 * Task Master called Duke to manage those pesky tasks.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList tasks;
    private Storage storage;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = storage.load();
    }

    public Duke() {
        new Duke("./data/");
    }

    private void run() {
        Ui.greet();

        Scanner userScan = new Scanner(System.in);
        while (userScan.hasNext()) {
            String input = userScan.nextLine();
            CommandParser keywordParser = new CommandParser(input);
            if (input.contentEquals("bye")) {
                Ui.goodbye();
                break;
            } else {
                CommandParser.COMMANDS enumCommand;
                try {
                    enumCommand = keywordParser.getCommand();
                } catch (TaskNotFoundException e) {
                    Ui.noTaskExceptionToast(e);
                    continue;
                }

                switch (enumCommand) {
                case BYE:
                    Ui.goodbye();
                    break;
                case LIST:
                    tasks.list();
                    break;
                case MARK:
                    Task doneTask = tasks.markTask(keywordParser.getTaskNo());
                    storage.saveTasks(tasks);
                    Ui.congrats(doneTask);
                    break;
                case UNMARK:
                    Task undoneTask = tasks.unmarkTask(keywordParser.getTaskNo());
                    storage.saveTasks(tasks);
                    Ui.undoneToast(undoneTask);
                    break;
                case DELETE:
                    Task deleted = tasks.deleteTask(keywordParser.getTaskNo());
                    storage.saveTasks(tasks);
                    break;
                case FIND:
                    ArrayList<Task> result = tasks.findTasks(keywordParser.getWord());
                    Ui.foundTaskToast();
                    Ui.printList(result);
                    break;
                default:
                    try {
                        tasks.addTask(input);
                        storage.saveTasks(tasks);
                    } catch (TaskNotFoundException e) {
                        Ui.noTaskExceptionToast(e);
                    } catch (ContentNotFoundException e) {
                        Ui.noContentExceptionToast(e);
                    } catch (DateNotFoundException e) {
                        Ui.noDateExceptionToast(e);
                    } catch (DateTimeParseException e) {
                        Ui.wrongDateFormatToast(e);
                    } finally {
                        continue;
                    }
                }
            }
        }

    }

    /**
     * Drives Duke class.
     * @param args array of arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/").run();
    }

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

        //Formatting the window to look as expected
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
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
                DialogueBox.getUserDialog(userText, new ImageView(user)),
                DialogueBox.getDukeDialog(dukeText, new ImageView(duke))
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

}
