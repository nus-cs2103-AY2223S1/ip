package Duke;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The class deals with the interaction with the user
 */
public class Ui extends Application {

    public Ui() {}

    private ScrollPane scrollPane;
    private static VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    Duke dukey;

    @Override
    public void start(Stage stage) {
        dukey = new Duke("data/duke.txt");

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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        printHelloMsg();
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );
        dukey.takeCommand(userInput.getText());
        userInput.clear();
    }

    public void closeApplication() {
        Platform.exit();
    }

    /**
     * The method is a static and takes in a parameter
     * @param tasks The input to be received
     */
    public void printList(TaskList tasks) {
        String tasksStr = "Here you tasks:\n";
        for (int j = 0; j < tasks.getSize(); j++) {
            tasksStr = tasksStr.concat((j + 1) + ". " + tasks.getTask(j).toString() + "\n");
        }
        tasksStr.concat("Stop asking me to do stuff");
        Label dukeText = new Label(tasksStr);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * The method is a static and prints a statement
     */
    public void printHelloMsg() {
        Label dukeText = new Label("Duke what u want");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        System.out.println();
    }

    /**
     * The method is a static and takes in two parameter
     * @param task of type Task
     * @param size of type int
     */
    public void printAddSuccessfulMsg(Task task, int size) {
        String msg = "Okok.\n"
                + task.toString()
                + " added. \nNow got " + size + " tasks already, stop adding please";
        Label dukeText = new Label(msg);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * The method is a static and takes in a parameter
     * @param tasks The input to be received
     */
    public void printFindTasks(ArrayList<Task> tasks) {
        String tasksStr = "Here the matching tasks:\n";
        for (int i = 0; i < tasks.size(); i ++) {
            tasksStr = tasksStr.concat((i + 1) + ". " + tasks.get(i) + "\n");
        }
        tasksStr.concat("stop asking me to do stuff");
        Label dukeText = new Label(tasksStr);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * The method is a static and takes in two parameter
     * @param task of type Task
     * @param size of type int
     */
    public void printDeleteSuccessfulMsg(Task task, int size) {
        Label dukeText = new Label("Kk. Removed:\n" + task.toString() +
                "\nNow got " + size + " tasks in the list. Stop ordering me around");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * The method is a static and takes in a parameter
     * @param task The input to be received
     */
    public void printMarkTaskSuccessfulMsg(Task task) {
        Label dukeText = new Label("Kk marked (" + task + ") already");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * The method is a static and takes in a parameter
     * @param task The input to be received
     */
    public void printUnMarkTaskSuccessfulMsg(Task task) {
        Label dukeText = new Label("OKok unmarked (" + task + ") already");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * The method is a static and prints a statement
     */
    public void printDontUnderstandMsg() {
        Label dukeText = new Label("What you talking, type also dont want type properly");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * The method is a static and takes in a parameter
     * @param typeStr The input to be received
     */
    public void printDescriptionCantBeEmptyMsg(String typeStr) {
        Label dukeText = new Label("Sorry this not your brain, " + typeStr + " cannot be empty");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * The method is a static and takes in a parameter
     * @param e The input to be received
     */
    public void printErrorMessage(String e) {
        System.out.println(e);
    }
}
