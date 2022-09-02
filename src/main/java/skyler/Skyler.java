package skyler;

import java.io.IOException;
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


/**
 * Represents a personal chat assistant
 */
public class Skyler extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui = new Ui(); //remove this
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image skyler = new Image(this.getClass().getResourceAsStream("/images/Skyler.png"));

    public Skyler() {}

    /**
     * Creates a personal chat assistant object
     *
     * @param filePath Filepath for storage of task data, relative to project folder.
     */
    public Skyler(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage, storage.load());
        } catch (SkylerException e) {
            taskList = new TaskList(storage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.parser = new Parser(taskList);
    }

    /**
     * Activates the personal chat assistant
     */
    public void run() {
        ui.greet();

        int active = 1;

        try (Scanner sc = new Scanner(System.in)) {
            while (active == 1 && sc.hasNext()) {
                String description = sc.nextLine();
                active = parser.parse(description);
            }
        } catch (EmptyDescriptionException e) {
            ui.showEmptyDescriptionError();
        } catch (TaskNotRecognisedException e) {
            ui.showTaskNotRecognisedError();
        } finally {
            ui.bye();
        }
    }

    public static void main(String[] args) {
        new Skyler("data/skyler.txt").run();
    }

    @Override
    public void start(Stage stage) {

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.getChildren().addAll(
                DialogBox.getSkylerDialog(new Label(ui.greet()), new ImageView(skyler))
        );
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Skyler");
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

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Skyler's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label skylerText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getSkylerDialog(skylerText, new ImageView(skyler))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Skyler heard: " + input;
    }
}
