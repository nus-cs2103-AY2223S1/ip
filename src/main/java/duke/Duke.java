package duke;

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

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents Duke, a Personal Assistant Chatterbot that helps a person to keep track of various things.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isActive = true;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Constructor of Duke.
     */
    public Duke() {
        try {
            storage = new Storage("./duke.txt");
            tasks = storage.load();
            ui = new Ui();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Add a Task to Duke.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) throws DukeException { // error if description is empty
        assert tasks != null;
        assert task != null;
        if (task.description.isBlank()) {
            throw new DukeException("Task description is empty!");
        }
        tasks.add(task);
        ui.addTask(task);
        ui.infoCount(tasks.size());
    }

    /**
     * Print all current tasks to the UI.
     */
    public void printTasks() {
        assert tasks != null;
        ui.printTasks(tasks);
    }

    /**
     * Mark a Task as done or not done.
     *
     * @param index Index of Task to mark.
     * @param isDone Boolean to mark the Task as done or not done.
     */
    public void markTask(int index, boolean isDone) throws DukeException {
        assert tasks != null;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bound!");
        }
        Task task = tasks.get(index);
        assert task != null;
        if (isDone) {
            task.markAsDone();
            ui.markAsDone(task);
        } else {
            task.markNotDone();
            ui.markNotDone(task);
        }
    }

    /**
     * Delete a Task.
     *
     * @param index Index of Task to delete.
     */
    public void deleteTask(int index) throws DukeException {
        assert tasks != null;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bound!");
        }
        Task task = tasks.remove(index);
        assert task != null;
        ui.deleteTask(task);
        ui.infoCount(tasks.size());
    }

    /**
     * Print all tasks that matches the keyword to the UI.
     *
     * @param keyword Keyword to match.
     */
    public void findTasks(String keyword) {
        assert keyword != null;
        TaskList matches = (TaskList) tasks.clone();
        assert matches != null;
        matches.removeIf(task -> !task.toString().toLowerCase().contains(keyword.toLowerCase()));
        ui.findTasks(matches);
    }

    /**
     * Prints the nearest date without task to the UI.
     */
    public void findFreeTimes() {
        LocalDate now = LocalDate.now();
        ArrayList<Long> distances = new ArrayList<>();
        tasks.forEach(task -> {
            if (task.type == 'D') {
                distances.add(((Deadline) task).deadline.toEpochDay() - now.toEpochDay());
            } else if (task.type == 'E') {
                distances.add(((Event) task).time.toEpochDay() - now.toEpochDay());
            }
        });
        distances.sort((a, b) -> (int) (a - b));
        int counter = 1;
        for (Long distance : distances) {
            if (distance > counter) {
                break;
            } else if (distance == counter) {
                counter++;
            }
        }
        ui.findFreeTimes(now.plusDays(counter));
    }

    /**
     * Exit the Duke Chatterbot.
     */
    public void exit() {
        assert isActive;
        isActive = false;
        ui.close();
    }

    /**
     * Starts the Duke GUI.
     *
     * @param stage Stage to show the GUI elements.
     */
    @Override
    public void start(Stage stage) {
        /* Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage. */

        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Takes user input and returns Duke's response to the input.
     *
     * @param input User input.
     * @return Duke's response to the input.
     */
    protected String getResponse(String input) {
        assert input != null;
        try {
            Command command = Parser.parseInput(input);
            command.run(this);
            storage.save(tasks);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
        return ui.collect();
    }
}
