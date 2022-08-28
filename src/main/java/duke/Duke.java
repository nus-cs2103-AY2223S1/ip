package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.geometry.Insets;
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

public class Duke extends Application {

    // MESSAGES
    private static final String markMessage = "Nice! I've marked this task as done:";
    private static final String unmarkMessage = "OK, I've marked this task as not done yet:";
    private static final String deleteMessage = "OK, I've deleted this task:";
    private static final String addedMessage = "Got it! I've added this task:";
    private static final String invalidCommandMessage = "I don't know what you mean.";

    // FILE PATH
    private static final String saveFilePath = "data/duke.txt";

    // IMAGES
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image lurchImage = new Image(this.getClass().getResourceAsStream("/images/DaLurch.png"));

    // UI OBJECTS
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // INSTANCE VARIABLES
    private boolean isTerminated = false;
    private final TaskList tasks;
    private final Storage storage;

    public Duke() {
        this.storage = new Storage(saveFilePath);
        this.tasks = new TaskList(storage.load());
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Terminates Duke application.
     *
     */
    public void terminate() {
        this.isTerminated = true;
        Ui.showTermination();
    }

    /**
     * Runs Duke application.
     *
     */
    public void run() {
        Ui.showGreeting();
        Scanner scanner = new Scanner(System.in);
        while (!this.isTerminated) {
            try {
                Parser currentCommand = new Parser(scanner.nextLine());
                String direction = currentCommand.getDirection();
                String meta = currentCommand.getMeta();
                switch (direction) {
                    case "bye":
                        this.terminate();
                        break;
                    case "list":
                        Ui.showTasksList(this.tasks);
                        break;
                    case "unmark":
                        Task unmarked = this.tasks.unmark(currentCommand.extractIndex());
                        Ui.showTaskStatus(unmarkMessage, unmarked);
                        break;
                    case "mark":
                        Task marked = this.tasks.mark(currentCommand.extractIndex());
                        Ui.showTaskStatus(markMessage, marked);
                        break;
                    case "delete":
                        Task deleted = this.tasks.delete(currentCommand.extractIndex());
                        Ui.showTaskStatus(deleteMessage, deleted);
                        break;
                    case "todo":
                        if (meta == null) throw new DukeException("Description cannot be empty");
                        Task todo = this.tasks.add(new Todo(meta));
                        Ui.showTaskStatus(addedMessage, todo);
                        break;
                    case "deadline":
                        if (meta == null) throw new DukeException("Description cannot be empty");
                        Task deadline = this.tasks.add(new Deadline(meta));
                        Ui.showTaskStatus(addedMessage, deadline);
                        break;
                    case "event":
                        if (meta == null) throw new DukeException("Description cannot be empty");
                        Task event = this.tasks.add(new Event(meta));
                        Ui.showTaskStatus(addedMessage, event);
                        break;
                    case "find":
                        if (meta == null) throw new DukeException("Query cannot be empty");
                        TaskList filtered = this.tasks.filter(meta);
                        if (filtered.getSize() == 0) throw new DukeException("No results found");
                        Ui.showTasksList(filtered);
                        break;
                    default:
                        throw new DukeException(invalidCommandMessage);
                }
                if (!direction.equals("bye") && !direction.equals("list")) {
                    this.storage.save(this.tasks);
                }
            } catch (DukeException e) {
                Ui.showErrorMessage(e);
            }
        }
    }

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

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Lurch");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(400, 520);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPadding(new Insets(6,6,24,6));

        scrollPane.setVvalue(1);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefSize(320, 60);
        sendButton.setPrefSize(80, 60);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked(event -> handleUserInput());

        userInput.setOnAction(event -> handleUserInput());

        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setPadding(new Insets(10));
        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label lurchText = new Label(getResponse(userInput.getText()));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getLurchDialog(lurchText, new ImageView(lurchImage))
        );

        userInput.clear();
    }

    private String getResponse(String input) {
        return "Lurch heard: " + input;
    }

    public static void main(String[] args) {
        new Duke(saveFilePath).run();
    }
}