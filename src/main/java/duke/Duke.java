package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke is a chatbot that records a list of Tasks that a user wishes to keep track of.
 */
public class Duke extends Application {
    private TaskList tasks = new TaskList();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/chimchar.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));

    /**
     * The main program.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        Storage.createDataFolder();
        Storage.createDataFile();
        Duke duke = new Duke();

        try {
            duke.tasks = Storage.initializeData();
        } catch (DukeException ex) {
            System.out.println("Error in initializing data!");
        }

        Ui.sayHello();

        while(true) {
            String action = duke.listen();

            if (Parser.containsExactKeyword(action)) {
                switch (action) {
                case Parser.EXACT_KEYWORD_BYE:
                    Ui.sayGoodbye();
                    break;
                case Parser.EXACT_KEYWORD_LIST:
                    duke.listTasks();
                    break;
                }

                if (action.equals(Parser.EXACT_KEYWORD_BYE)) {
                    break;
                }
            } else if (Parser.containsMarkKeyword(action)) {
                try {
                    int id = Parser.getSpecifier(action);

                    if (id > duke.tasks.size() || id < 1) {
                        throw new DukeException("Out of bounds index!");
                    }

                    switch (Parser.getNonexactKeyword(action)) {
                    case Parser.MARK_KEYWORD_MARK:
                        duke.markTask(duke, id);
                        break;
                    case Parser.MARK_KEYWORD_UNMARK:
                        duke.unmarkTask(duke, id);
                        break;
                    case Parser.MARK_KEYWORD_DELETE:
                        duke.tasks.deleteTask(id);
                        break;
                    }

                    Storage.updateData(duke.tasks);
                } catch (DukeException ex) {
                    Ui.echo("Index is out of bounds!");
                }
            } else if (Parser.containsTaskKeyword(action)) {
                try {
                    switch (Parser.getNonexactKeyword(action)) {
                    case Parser.TASK_KEYWORD_TODO:
                        duke.tasks.add(new Todo(action));
                        break;
                    case Parser.TASK_KEYWORD_DEADLINE:
                        if (!action.contains(Deadline.DELIMITER)) {
                            throw new DukeException("Missing Deadline delimiter!");
                        }
                        duke.tasks.add(new Deadline(action));
                        break;
                    case Parser.TASK_KEYWORD_EVENT:
                        if (!action.contains(Event.DELIMITER)) {
                            throw new DukeException("Missing Event delimiter!");
                        }
                        duke.tasks.add(new Event(action));
                        break;
                    }

                    Storage.updateData(duke.tasks);

                    Ui.echo("Added task:" +
                            duke.tasks.getLast().printTask());
                } catch (DukeException ex) {
                    Ui.echo("Incorrect use of " + Parser.getNonexactKeyword(action));
                }
            } else if (Parser.containsFindKeyword(action)) {
                duke.listMatchingTasks(action);
            } else {
                Ui.echo("I can't understand. Please enter a proper command, friend!");
            }
        }
    }

    /**
     * Starts Duke with a GUI.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Lists matching tasks according to user search.
     *
     * @param userInput the user input.
     */
    public void listMatchingTasks(String userInput) {
        this.tasks.printMatchingTasks(Parser.getTermTofind(userInput));
    }

    /**
     * Lists all tasks recorded by the user.
     */
    public void listTasks() {
        this.tasks.listAllTasks();
    }

    /**
     * Marks a task as done.
     *
     * @param interaction this duke session.
     * @param id the id of the task.
     */
    public void markTask(Duke interaction, int id) {
        Task task = interaction.tasks.get(id - 1);
        task.markAsDone();
        Ui.echo(String.format("Task %d: [%s] marked as done!",
                id,
                task.getDescription()));
    }

    /**
     * Marks a task as not done.
     *
     * @param interaction this duke session.
     * @param id the id of the task.
     */
    public void unmarkTask(Duke interaction, int id) {
        Task task  = interaction.tasks.get(id - 1);
        task.markAsNotDone();
        Ui.echo(String.format("Task %d [%s] marked as not done!",
                id,
                task.getDescription()));
    }

    /**
     * Listens to the user input.
     *
     * @return the user input.
     */
    public String listen() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
