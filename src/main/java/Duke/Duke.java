package Duke;
import java.util.Scanner;
import Duke.Storage.Storage;
import Duke.Exception.DukeException;
import Duke.GUI.DialogBox;
import Duke.GUI.GuiUi;
import Duke.Handler.Parser;
import Duke.Handler.Ui;
import Duke.Tasks.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * Duke is the main class that will save and run the program
 *
 * @author Fang Yiye
 */
public class Duke extends Application {
    private TaskList taskList;
    private static Ui ui;
    private Storage storage;
    private static Scanner sc;
    private GuiUi guiUi;

    private Button button;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));


    /**
     * Constructor of Duke to initialise ui, storage and scanner
     */
    public Duke() {
        ui = new Ui();
        guiUi= new GuiUi();
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.loadingError();
            taskList = new TaskList();
        }
    }


    /**
     * main methods that allow the project to run
     *
     * @param args
     * @throws DukeException by the parser if the parser parses an invalid output
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        Parser parser = new Parser(duke, ui);
        duke.run(parser);
    }


    /**
     * run the program
     *
     * @param parser which takes in the system input
     * @throws DukeException by the parser if the parser parses an invalid output
     */
    public void run(Parser parser) throws DukeException {
        ui.greet();
        while (sc.hasNextLine()) {
            parser.parse(sc.nextLine());
            storage.writeTasks(taskList);
        }
    }

    /**
     * Main entry point of javafx application
     *
     * @param stage container that hosts a scene
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("DuKe");
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        button = new Button();
        button.setText("SEND");


        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(scrollPane, userInput, button);

        scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        //format
        format(stage, layout, scrollPane, userInput, button);

        //functionality
        button.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    /**
     * Formats the application
     *
     * @param stage called Duke
     * @param layout created from start
     * @param scrollPane from start
     * @param userInput keyed in
     * @param button send button
     */
    public void format(Stage stage, AnchorPane layout, ScrollPane scrollPane, TextField userInput, Button button) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        layout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        button.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(button, 1.0);
        AnchorPane.setRightAnchor(button, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

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
    private void handleUserInput() throws DukeException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.getText(), user),
                DialogBox.getDukeDialog(dukeText.getText(), duke)
        );
        userInput.clear();
    }

    public String getResponse(String input) throws DukeException {
        String response = guiUi.enterText();
        if (input.equals("list")) {
            response = guiUi.displayTask();
        } else if (input.startsWith("mark")) {
            response = guiUi.displayMarkDone(input);
        } else if (input.startsWith("unmark")) {
            response = guiUi.displayUnmark(input);
        } else if (input.startsWith("todo")) {
            response = guiUi.displayTodo(input);
        } else if (input.startsWith("deadline")) {
            response = guiUi.displayDeadline(input);
        } else if (input.startsWith("event")) {
            response = guiUi.displayEvent(input);
        } else if (input.startsWith("delete")) {
            response = guiUi.displayDelete(input);
        } else if (input.startsWith("find")) {
            response = guiUi.displaySearchName(input);
        } else if (input.startsWith("tag")) {
            response = guiUi.tagTask(input);
        } else if (input.startsWith("print tag")) {
            response = guiUi.printTaskTag(input);
        } else if (input.startsWith("#")) {
            response = guiUi.searchTag(input);
        } else if (input.startsWith("remove tag")) {
            response = guiUi.removeTag(input);
        } else if (input.startsWith("dayFilter")){
            //starts with dateFilter
            response = guiUi.displaySearchDate(input);
        } else {
            response = "no such command, check user guide";
        }
        return response;
    }

    public static String trimUserInput(String userInput) {
        if (userInput.trim().contains(" ")) {
            return userInput.trim().substring(0, userInput.indexOf(" ")).trim();
        }
        return userInput;
    }

    public String displayBye() {
        storage.writeTasks(taskList);
        return "Bye! Hope to see you again soon!";
    }

    public String displayGreet() {
        return "Hello!, I'm Yiye.\nWhat can I do for you? ◠‿◠";
    }

}
