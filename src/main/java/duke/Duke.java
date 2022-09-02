package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the Duke bot.
 */
public class Duke extends Application {
    private static boolean isLoading = true;
    private static boolean isRunning = true;
    private static Scanner sc = new Scanner(System.in);
    private String filePath = "C:/Data/Duke.txt";

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for the Duke bot.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    /**
     * Stops the Duke bot.
     */
    public String stopBot() {
        Duke.isRunning = false;
        return "Byebye! See you again soon!\n"
                + "(Send another message or an empty message to automatically close.)";
    }

    /**
     * Get the storage associated with the Duke bot.
     *
     * @return Storage of the Duke bot.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Loads saved data from previous runs.
     */
    public void loadData() {
        try {
            this.storage.loadData(this, this.filePath);
        } catch (IOException e) {
            System.err.println("File not found.");
        }
        this.isLoading = false;
    }

    /**
     * Returns whether the Duke bot has finished loading.
     *
     * @return Boolean indicating whether the Duke bot has finished loading.
     */
    public boolean hasFinishedLoading() {
        return !this.isLoading;
    }

    /**
     * Prints a list of tasks on hand.
     *
     * @return Lists of tasks.
     */
    public String getList() {
        return this.tasks.getList();
    }

    /**
     * Marks the task as completed.
     *
     * @param i Index of the task to be marked as completed.
     * @return Associated message from Duke.
     */
    public String markTask(int i) {
        return this.tasks.markTask(i);
    }

    /**
     * Marks the task as not completed.
     *
     * @param i Index of the task to be marked as not completed.
     * @return Associated message from Duke.
     */
    public String unmarkTask(int i) {
        return this.tasks.unmarkTask(i);
    }

    /**
     * Adds a task without deadline.
     *
     * @param s Task description.
     * @return Associated message from Duke.
     */
    public String addTodo(String s) {
        return this.tasks.addTodo(s);
    }

    /**
     * Adds a task with deadline.
     *
     * @param s Task description.
     * @param d Deadline in LocalDate format.
     * @return Associated message from Duke.
     */
    public String addDeadline(String s, LocalDate d) {
        return this.tasks.addDeadline(s, d);
    }

    /**
     * Adds a task with deadline.
     *
     * @param s Task description.
     * @param d Deadline in String format.
     * @return Associated message from Duke.
     */
    public String addDeadline(String s, String d) {
        return this.tasks.addDeadline(s, d);
    }

    /**
     * Adds an event.
     *
     * @param s Event description.
     * @param d Event time in LocalDate format.
     * @return Associated message from Duke.
     */
    public String addEvent(String s, LocalDate d) {
        return this.tasks.addEvent(s, d);
    }

    /**
     * Adds an event.
     *
     * @param s Event description.
     * @param d Event time in String format.
     * @return Associated message from Duke.
     */
    public String addEvent(String s, String d) {
        return this.tasks.addEvent(s, d);
    }

    /**
     * Deletes the task from the list.
     *
     * @param i Index of the task to be deleted.
     * @return Associated message from Duke.
     */
    public String deleteTask(int i) {
        return this.tasks.deleteTask(i);
    }

    /**
     * Parses a message written to the Duke bot.
     *
     * @param s Message to be parsed.
     * @return Associated message from Duke.
     */
    public String parse(String s) {
        return this.parser.parse(this, s, this.hasFinishedLoading());
    }

    /**
     * Prints a list of tasks with the matching input string.
     *
     * @param s String to request for tasks with the matching string.
     * @return List of tasks with matching string.
     */
    public String find(String s) {
        return this.tasks.find(s);
    }

    //@@author chengda300
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    @Override
    public void start(Stage stage) {
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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }
    //@@author

    /**
     * Returns the associated message from Duke after parsing, or closes Duke if instructed so previously.
     *
     * @return Associated message from Duke.
     */
    protected String getResponse(String input) {
        if (!this.isRunning) {
            System.exit(0);
        }
        return this.parse(input);
    }
}
