package duke;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;




/**
 *  duke.Duke Class
 *  The class which stores inputs and interacts with user
 *
 * @author Kang Qiao
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/paimon.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/gigachad.jpg"));

    /**
     * Constructor of the duke class and loads the saved file into the storage.
     *
     */
    public Duke()  {
        taskList = new TaskList();
        ui = new Ui(taskList);
        storage = new Storage("data/duke.txt");
        parser = new Parser();
        //Initialises the saved file into the taskList.
        //load the file ONLY in the constructor so that multiple calls in parse
        //will not duplicate it via updateFile.
        storage.inputSavedFile(taskList);
    }

    /**
     * Runs the duke program.
     *
     * @throws DukeException
     */
    public void run() throws DukeException {
        System.out.println(ui.getHello());
        try {
            File dukeFile = new File("data/duke.txt");
            dukeFile.getParentFile().mkdirs();//create the directory
            if (dukeFile.createNewFile()){
                System.out.println("new file created!");
            } else {
               storage.readAndSaveFile(dukeFile, taskList);
            }
        } catch (IOException e) {
            System.out.println("Error in creating file");
        }
        System.out.println(ui.goodbye());
    }

    @Override
    public void start(Stage stage) {

        storage.inputSavedFile(taskList);

        //for reading the file.
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen.
        stage.show(); // Render the stage.

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        //exception added
        sendButton.setOnMouseClicked((event) -> {
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
    //exception added
    private void handleUserInput() throws DukeException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox("hello", user),
                new DialogBox("no", duke)
        );
        userInput.clear();
    }
    //exception added

    /**
     * Returns the response to the user after parsing through his command.
     *
     * @param input
     * @return a String of representing the response a user gets from his input.
     * @throws DukeException
     */
    String getResponse(String input) throws DukeException {
        //parses the user input and store the input accordingly in storage and taskList.
        return parser.parseInstruction(storage, taskList, input);
    }



    /**
     *The Main function for interaction between the user and DUKE.
     */
    public static void main(String[] args) throws DukeException {
        Duke dukeBot = new Duke();
        dukeBot.run();
        System.out.println("hello");
    }
}
