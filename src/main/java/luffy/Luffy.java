package luffy;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
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
 * Luffy is a todolist program. Built for CS2103T Individual Project 2022 S1.
 *
 * @author Silas Tay A0233425M
 */
public class Luffy extends Application{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/luffy.jpeg"));

    private static final String FILE_PATH = "./data/data.txt";

    /**
     * Construtor for Luffy object.
     */
    public Luffy() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Luffy program.
     */
    public void run() {
        this.ui.printWelcome();

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        //Getting inputs:
        while (true) {
            String nextLine = scanner.nextLine();
            if (nextLine.equals("bye")) {
                break;
            } else {
                parser.parse(nextLine, this.tasks);
            }
            this.storage.updateSaveFile(this.tasks, FILE_PATH);
        }
    }

    @Override
    public void start(Stage stage) {
        //Container for content of the chat to scroll:
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
        stage.setTitle("Luffy");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Formats User Input into elements to show response in GUI.
     */
    private void handleUserInput() {
        if (userInput.getText().equals("bye")) {
            Platform.exit();
        }
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(this.parser.parse(userInput.getText(), this.tasks));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getLuffyDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        storage.updateSaveFile(this.tasks, FILE_PATH);
    }

    /**
     * Main method for Luffy.
     *
     * @param args User commandline input
     */
    public static void main(String[] args) {
        new Luffy().run();
    }
}
