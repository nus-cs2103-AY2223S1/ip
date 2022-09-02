package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.ui.DialogBox;
import duke.ui.Ui;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * The main class of the Duke program.
 */
public class Duke extends Application {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogueContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Constructs a new {@code Duke} using a String path.
     */
    public Duke() {
        String home = System.getProperty("user.home");
        Path filepath = Paths.get(home, "Desktop", "duke.txt");
        storage = new Storage(filepath.toString());
        ui = new Ui();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<>());
            System.out.println(e);
        }
    }

    /**
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        scrollPane = new ScrollPane();
        dialogueContainer = new VBox();

        Label greetingLabel = new Label(ui.printGreeting());
        dialogueContainer.getChildren().addAll(DialogBox.getDukeDialog(greetingLabel, new ImageView(duke)));
        
        scrollPane.setContent(dialogueContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogueContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(400.0, 570.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogueContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Runs the Duke object after instantiation.
     */
    private String getResponse(String inputText) {
        Parser parser = new Parser(inputText);

        try {
            Command keyword = parser.getKeyword();
            String content = parser.getContent();
            switch (keyword) {
            case BYE:
                return ui.printBye();
            case LIST:
                return taskList.printTasks();
            case MARK:
                return taskList.markTask(content);
            case UNMARK:
                return taskList.unmarkTask(content);
            case DELETE:
                return taskList.deleteTask(content);
            case TODO: {
                ToDo newTask = new ToDo(content);
                return taskList.addTask(newTask);
            }
            case EVENT: {
                String[] contentArray = parser.getContentForEvent();
                LocalDateTime dateTime = Parser.stringToDateTime(contentArray[1]);

                Event newTask = new Event(contentArray[0], dateTime);
                return taskList.addTask(newTask);
            }
            case DEADLINE: {
                String[] contentArray = parser.getContentForDeadline();
                LocalDateTime dateTime = Parser.stringToDateTime(contentArray[1]);

                Deadline newTask = new Deadline(contentArray[0], dateTime);
                return taskList.addTask(newTask);
            }
            case FIND:
                return taskList.findTasks(content);
            }
            storage.save(taskList.getTasks());
        } catch (Exception e) {
            return e.getMessage();
        }
        return "IDK";
    }
}
