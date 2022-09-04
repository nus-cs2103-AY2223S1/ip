import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import objects.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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

public class Duke {
    // IMPORTANT: Configure isTest to true if you are running ./runtest.sh in text-ui-test
    private static final String JAR_FILE_PATH = "data/tasks.txt";
    private static final Boolean IS_TEST = false;
    private static final String FILE_PATH = IS_TEST ? "data/tasksUiTest.txt" : JAR_FILE_PATH;

    private static Ui ui = new Ui();
    private static TaskList taskList = new TaskList();
    private static List<Task> tasks = taskList.getTasks();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();

//    @FXML
//    private ScrollPane scrollPane;
//    @FXML
//    private VBox dialogContainer;
//    @FXML
//    private TextField userInput;
//    @FXML
//    private Button sendButton;
//    private Scene scene;

    /**
     * Main function of the app.
     *
     * The first string consists of the command keyword. The possible keywords are defined
     * as an Enum. If no keyword is detected, the app alerts the user.
     *
     * If a keyword is detected, an action corresponding to the keyword will be executed.
     * The descriptions or additional data written after the keyword will be parsed,
     * and the relevant actions will be executed.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        try {
            // Assume that user has duke.jar in an empty folder
            File dir = new File("sample");
            File f = new File("sample/tasks.txt");
            // Create a new folder called data in the empty folder
            // Create a new file called tasks.txt in that folder to store data
            if (dir.mkdir()) {
                f.createNewFile();
            }

            Ui.printIntroduction();
            // Load the tasks from the file tasks.txt
            storage.loadTasks(FILE_PATH, tasks);
            parser.parseCommand(tasks);
        } catch (FileNotFoundException e1) {
            System.out.println(e1.getMessage());
        } finally {
            if (!IS_TEST) {
                storage.saveTasks(FILE_PATH, tasks);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duck heard: " + input;
    }

//    @Override
//    public void start(Stage stage) {
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//
//        // Styling
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//        mainLayout.setPrefSize(400.0, 600.0);
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        userInput.setPrefWidth(325.0);
//        sendButton.setPrefWidth(55.0);
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // Handling user input from text field
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        // Automatic scroll down when the texts generated on the screen goes beyond the screen size
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }


}
