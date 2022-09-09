package duke;
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

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for the bot Duke
 *
 * @author Kang Zong Xian
 */
public class Duke extends Application {

    // Attributes of a Duke object
    private static final String FILEDESTINATION = "./src/main/duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // The images used for Duke
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/user2.png"));

    /**
     * The constructor for the Duke class
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEDESTINATION);
        taskList = new TaskList();
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

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
    private void handleUserInput() throws IOException {
        String userText = userInput.getText();
        String dukeText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) throws IOException {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        try {
            String userCommand = input;
            if (userCommand.equals("bye")) {
                return ui.bye();
            } else if (userCommand.equals("list")) {
                return ui.showTaskList();
            } else {
                // Get all the words the user has typed
                String[] words = userCommand.split(" ");
                // Check if user wants to mark task
                if (Parser.isMarkTask(words)) {
                    int taskNumber = Integer.parseInt(words[1]);
                    // Check if user enters a number out of range
                    if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
                        throw new DukeException("Number out of range!");
                    } else {
                        return ui.markTaskDoneAndPrintOutput(taskNumber);
                    }
                    // Checks if user wants to unmark task
                } else if (Parser.isUnmarkTask(words)) {
                    int taskNumber = Integer.parseInt(words[1]);
                    // Check if user enters a number out of range
                    if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
                        throw new DukeException("Number out of range!");
                    } else {
                        return ui.markTaskNotDoneAndPrintOutput(taskNumber);
                    }
                    // Check if user wants to delete a task
                } else if (Parser.isDeleteTask(words)) {
                    int taskNumber = Integer.parseInt(words[1]);
                    if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
                        throw new DukeException("Number out of range!");
                    } else {
                        return ui.markTaskDeletedAndPrintOutput(taskNumber);
                    }
                } else {
                    // User is trying to add a new to-do / deadline / event
                    if (Parser.isAddTodoTask(words)) {
                        return createAndAddTodo(words);
                    } else if (Parser.isAddDeadlineTask(words)) {
                        return createAndAddDeadline(words);
                    } else if (Parser.isAddEventTask(words)) {
                        return createAndAddEvent(words);
                    } else if (Parser.isFindTask(words)) {
                        String keywords = Parser.joinString(words, 1);
                        keywords = keywords.substring(0, keywords.length() - 1);
                        return findMatchingTasks(taskArrayList, keywords);
                    } else {
                        String outputString = "I don't know what you mean, so I will just echo you\n";
                        outputString += input;
                        return outputString;
                    }
                }
            }
        } catch (DukeException dukeException) {
            System.out.println(dukeException.getMessage());
        } finally {
            storage.saveTasks();
        }
        return "Done";
    }

    /**
     * Find the matching tasks based on keyword search
     * @param taskArrayList the array list of all the tasks
     * @param keywords the keywords the user typed
     * @return a String representing all the tasks that match the keywords
     */
    public String findMatchingTasks(List<Task> taskArrayList, String keywords) {
        String outputString = "";
        outputString += "Here are the matching tasks in your list:\n";

        for (Task task : taskArrayList) {
            if (task.getDescription().contains(keywords)) {
                outputString += task.toString() + "\n";
            }
        }
        return outputString;

    }

    /**
     * The method to run the Duke bot
     * @throws DukeException exception to be thrown regarding DukeException
     * @throws IOException exception to be thrown regarding IOException
     */
    public void run() throws DukeException, IOException {
        storage.loadTasks();
        ui.greet();
        ui.showTaskList();

        List<Task> taskArrayList = TaskList.getTaskArrayList();

        // Boolean value to check if user wants to leave
        boolean isQuit = false;

        while (!isQuit) {
            try {
                String userCommand = ui.getCommand();
                if (userCommand.equals("bye")) {
                    isQuit = true;
                    ui.bye();
                } else if (userCommand.equals("list")) {
                    ui.showTaskList();
                } else {
                    // Get all the words the user has typed
                    String[] words = userCommand.split(" ");
                    // Check if user wants to mark task
                    if (Parser.isMarkTask(words)) {
                        int taskNumber = Integer.parseInt(words[1]);
                        // Check if user enters a number out of range
                        if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
                            throw new DukeException("Number out of range!");
                        } else {
                            ui.markTaskDoneAndPrintOutput(taskNumber);
                        }
                        // Checks if user wants to unmark task
                    } else if (Parser.isUnmarkTask(words)) {
                        int taskNumber = Integer.parseInt(words[1]);
                        // Check if user enters a number out of range
                        if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
                            throw new DukeException("Number out of range!");
                        } else {
                            ui.markTaskNotDoneAndPrintOutput(taskNumber);
                        }
                        // Check if user wants to delete a task
                    } else if (Parser.isDeleteTask(words)) {
                        int taskNumber = Integer.parseInt(words[1]);
                        if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
                            throw new DukeException("Number out of range!");
                        } else {
                            ui.markTaskDeletedAndPrintOutput(taskNumber);
                        }
                    } else {
                        // User is trying to add a new to-do / deadline / event
                        if (Parser.isAddTodoTask(words)) {
                            createAndAddTodo(words);
                        } else if (Parser.isAddDeadlineTask(words)) {
                            createAndAddDeadline(words);
                        } else if (Parser.isAddEventTask(words)) {
                            createAndAddEvent(words);
                        } else if (Parser.isFindTask(words)) {
                            String keywords = Parser.joinString(words, 1);
                            keywords = keywords.substring(0, keywords.length() - 1);
                            System.out.println("Here are the matching tasks in your list:");
                            for (Task task : taskArrayList) {
                                if (task.getDescription().contains(keywords)) {
                                    System.out.println(task.toString());
                                }
                            }
                        } else {
                            throw new DukeException("I'm sorry, I don't know what that means!");
                        }
                    }
                }
            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
            } finally {
                storage.saveTasks();
            }
        }
    }

    public String findMatchingTasksFromKeywords(List<Task> taskArrayList, String keywords) {
        String outputString = "";
        outputString += "Here are the matching tasks in your list:\n";
        List<String> taskStrings = taskArrayList.stream().filter(x -> x.getDescription().contains(keywords))
                .map(Task::toString).collect(Collectors.toList());

        for (String toAdd : taskStrings) {
            outputString += toAdd;
        }

        return outputString;

    }

    /**
     * Creates a to-do object
     * @param wordsArray the array of words entered by the user
     * @throws DukeException exception to be thrown regarding DukeException
     */
    public String createAndAddTodo(String[] wordsArray) throws DukeException {
        String description = "";
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        if (wordsArray.length > 1) {
            description = Parser.joinString(wordsArray, 1);
            // Correct an extra spacing at the end
            description = description.substring(0, description.length() - 1);
        }
        Todo newTodo = new Todo(description);
        taskArrayList.add(newTodo);
        return ui.printAddedTask(newTodo);
    }

    /**
     * Creates a deadline object
     * @param wordsArray the array of words entered by the user
     * @throws DukeException exception to be thrown regarding DukeException
     */
    public String createAndAddDeadline(String[] wordsArray) throws DukeException {
        String remainingDescription = "";
        String description = "";
        String[] remainingWords;
        String[] dateTimeArray = null;
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        String by = "";
        if (wordsArray.length > 1) {
            // Remaining description are the words after the task description
            remainingDescription = Parser.joinString(wordsArray, 1);
            remainingWords = remainingDescription.split(" /by ");
            description = remainingWords[0];
            by = remainingWords[1];
            dateTimeArray = by.split(" ");
            // Cut down a white spacing at the end
            by = by.substring(0, by.length() - 1);
        }
        // Assert dateTimeArray not null
        assert dateTimeArray != null;
        LocalDate byDate = Parser.createLocalDate(dateTimeArray[0].strip());
        Deadline newDeadline = new Deadline(description, byDate, by);
        taskArrayList.add(newDeadline);
        return ui.printAddedTask(newDeadline);
    }

    /**
     * Creates an event object
     * @param wordsArray the array of words entered by the user
     * @throws DukeException exception to be thrown regarding DukeException
     */
    public String createAndAddEvent(String[] wordsArray) throws DukeException {
        // If user is trying to add an event, save the description and the 'at' date
        // Have a default value in case the user did not add any description
        String description = "";
        String[] remainingWords;
        String at = "";
        String remainingDescription = "";
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        if (wordsArray.length > 1) {
            // Remaining description are the words after the task description
            remainingDescription = Parser.joinString(wordsArray, 1);
            remainingWords = remainingDescription.split(" /at ");
            description = remainingWords[0];
            at = remainingWords[1];
            // Cut down a white spacing at the end
            at = at.substring(0, at.length() - 1);
        }
        Event newEvent = new Event(description, at);
        taskArrayList.add(newEvent);
        return ui.printAddedTask(newEvent);
    }

    /**
     * Get multiple task descriptions depending on the index values theu ser enters
     * @param taskIndexArray the array formed using varargs
     * @return a string indicating all the task descriptions for the task indexes the user enters
     */
    public String getMultipleTaskDescriptions(int... taskIndexArray) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        String outputString = "";
        for (int i = 0; i < taskIndexArray.length; i++) {
            int taskIndex = taskIndexArray[i];
            outputString += taskArrayList.get(taskIndex).toString();
        }
        return outputString;
    }

    /**
     * The main function
     * @param args arguments
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }



}
