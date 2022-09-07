package duke;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.SaveTasks;
import duke.util.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

import java.nio.file.Paths;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private static final String DIR = "../data";
    private static final String FILENAME = "duke.txt";
    private static final String FILEPATH = String.valueOf(Paths.get(DIR, FILENAME));
    private static final String LINE = "\n----------------------------------------------------------------\n";
    private Ui ui;
    private SaveTasks savedTasks;
    private TaskList taskList;

    public Duke() {

    }
    /**
     * Constructor for Duke.
     *
     * @param fileDir  the directory of an existing text database
     * @param filePath the path to an existing text database
     */
    public Duke(String fileDir, String filePath) {
        this.savedTasks = new SaveTasks(fileDir, filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.savedTasks.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
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
    }

    public void run() {
        while (true) {
            String temp = this.ui.readInput();
            try {
                if (temp.equals("bye")) {
                    System.out.println(LINE + "Bye. Hope to see you again!" + LINE);
                    this.ui.closeInput();
                    this.savedTasks.save(taskList);
                    break;
                }
                Parser.parseCommand(temp, taskList);
            } catch (DukeException err) {
                this.ui.closeInput();
                this.savedTasks.save(taskList);
                System.out.println(err);
                break;
            }

        }
    }

    public static void main(String[] args) {
        new Duke(DIR, FILEPATH).run();
    }
}
