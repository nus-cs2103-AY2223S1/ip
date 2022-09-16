package duke;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
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
 * Is an interactive chatbot that keep tracks of to-do inputted by the user.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String HORIZONTAL_LINE_BREAK = "-------------------------";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/mikasa.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/eren.jpg"));

    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("I am unable to create a new file.");
        }
    }

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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
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
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        Duke duke = new Duke();
        Parser parser = new Parser(duke);
        return parser.initialise(input);
    }



    //Solution adapted from https://github.com/nus-cs2103-AY2223S1/ip/commit/1425eadf831d33ce6909694a2c3d5d58670aacd9

 /*   public void run(Parser parser) {
        ui.hello();
        parser.initialise();
        ui.goodBye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Parser parser = new Parser(duke);
        duke.run(parser);
    }
*/

    /**
     * Returns the list of all current tasks.
     */
    public String showList() {
        return ui.printList(tasks);
    }

    /**
     * Calls TaskList object to handle a ToDo task inputted by the user.
     * @param description String description of the task.
     * @throws DukeException
     */
    public String setToDo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of todo cannot be empty");
        }
        Task toDo = new ToDo(description);
        tasks.addTask(toDo);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printToDo(toDo, size);
    }

    /**
     * Calls TaskList object to handle a DeadLine task inputted by the user.
     * @param description String description of the task.
     * @param by String representation of the deadline of the task.
     */
    public String setDeadLine(String description, String by) {
        Task deadLine = new Deadline(description, by);
        tasks.addTask(deadLine);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printDeadLine(deadLine, size);
    }

    /**
     * Calls TaskList object to handle a Event task inputted by the user.
     * @param description String description of the task.
     * @param at String representation of when the task will happen.
     */
    public String setEvent(String description, String at) {
        Task event = new Event(description, at);
        tasks.addTask(event);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printEvent(event, size);
    }

    /**
     * Calls TaskList object to handle the marking of a task.
     * @param index Index of the task to be marked.
     * @throws DukeException
     */
    public String mark(int index) throws DukeException {
        tasks.markTask(index);
        Task markedTask = tasks.getTask(index);
        storage.save(tasks);
        return ui.printMark(markedTask);
    }

    /**
     * Calls TaskList object to handle the unmarking of a task.
     * @param index Index of the task to be unmarked.
     * @throws DukeException
     */
    public String unmark(int index) throws DukeException {
        tasks.unMarkTask(index);
        Task unmarkedTask = tasks.getTask(index);
        storage.save(tasks);
        return ui.printUnMark(unmarkedTask);
    }

    /**
     * Calls TaskList object to handle the deletion of a task.
     * @param index Index of the task to be deleted.
     * @throws DukeException
     */
    public String delete(int index) throws  DukeException {
        Task taskToDelete = tasks.deleteTask(index);
        int size = tasks.getSize();
        storage.save(tasks);
        return ui.printDelete(taskToDelete, size);
    }

    public String find(String keyword) {
        ArrayList<Task> taskList = tasks.getAllTasks();
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : taskList) {
            String description = task.description;
            if (description.contains(keyword)) {
                filteredTasks.add(task);
            }
        }
       return ui.printFind(filteredTasks);
    }

    public String sayBye() {
        return ui.goodBye();
    }

}
