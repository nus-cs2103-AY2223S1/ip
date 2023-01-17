package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * This class is responsible for the bot Duke
 *
 * @author Kang Zong Xian
 */
public class Duke {

    // Attributes of a Duke object
    private static final String FILEDESTINATION = "storage/duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // The images used for Duke
    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user2.png")));

    /**
     * The constructor for the Duke class
     */
    public Duke() throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(FILEDESTINATION);
        taskList = new TaskList();
        storage.loadTasks();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) throws IOException {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        try {
            String userCommand = input;
            if (userCommand.equals("help1")) {
                return ui.help1();
            } else if (userCommand.equals("help2")) {
                return ui.help2();
            } else if (userCommand.equals("bye")) {
                storage.saveTasks();
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
                return ui.bye();
            } else if (userCommand.equals("hello")) {
                return ui.greet();
            } else if (userCommand.equals("list")) {
                return ui.showTaskList();
            } else {
                // Get all the words the user has typed
                String[] words = userCommand.split(" ");
                // Check if user wants to mark task
                return checkTaskCommands(words, taskArrayList);
            }
        } catch (DukeException dukeException) {
            System.out.println(dukeException.getMessage());
        } finally {
            storage.saveTasks();
        }
        return "Invalid command, enter 'help1' or 'help2' to see the list of commands again.";
    }

    /**
     * Commands involved when user wants to mark, unmark or delete a task
     * @param words the array of words the user has entered
     * @param taskArrayList the current task array list
     * @return a String representing the task
     * @throws DukeException the exception to be thrown
     */
    public String checkTaskCommands(String[] words, List<Task> taskArrayList) throws DukeException {
        if (Parser.isMarkTask(words)) {
            int taskNumber = Integer.parseInt(words[1]);
            return markTask(taskNumber, taskArrayList);
            // Checks if user wants to unmark task
        } else if (Parser.isUnmarkTask(words)) {
            int taskNumber = Integer.parseInt(words[1]);
            return unmarkTask(taskNumber, taskArrayList);
            // Check if user wants to delete a task
        } else if (Parser.isDeleteTask(words)) {
            int taskNumber = Integer.parseInt(words[1]);
            return deleteTask(taskNumber, taskArrayList);
        } else {
            // User is trying to add a new to-do / deadline / event
            return taskAddingFinding(words, taskArrayList);
        }
    }

    /**
     * Commands involved when user wants to create a task or find a task
     * @param words the array of words the user has entered
     * @param taskArrayList the current task array list
     * @return a String representing the task
     * @throws DukeException the exception to be thrown
     */
    public String taskAddingFinding(String[] words, List<Task> taskArrayList) throws DukeException {
        if (Parser.isAddTodoTask(words)) {
            return createAndAddTodo(words);
        } else if (Parser.isAddDeadlineTask(words)) {
            return createAndAddDeadline(words);
        } else if (Parser.isAddEventTask(words)) {
            return createAndAddEvent(words);
        } else if (Parser.isFindTask(words)) {
            String keywords = Parser.joinString(words, 1);
            keywords = keywords.substring(0, keywords.length() - 1);
            String[] allKeywords = keywords.split(" ");
            return findMatchingTasks(taskArrayList, allKeywords);
        } else {
            return "I don't know what you mean, did you type an invalid command?\n"
                    + "Enter 'help1' or 'help2' to see the list of commands again";
        }

    }

    /**
     * Mark a task as done and return the string representing marking a task as done
     * @param taskNumber the index of the task
     * @param taskArrayList the list of tasks
     * @return a string representing the task that has been marked
     * @throws DukeException the exception to be thrown
     */
    public String markTask(int taskNumber, List<Task> taskArrayList) throws DukeException {
        // Check if user enters a number out of range
        if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
            throw new DukeException("Number out of range!");
        } else {
            return ui.markTaskDoneAndPrintOutput(taskNumber);
        }
    }

    /**
     * Un-marking a task as done and return the string representing un-marking a task as done
     * @param taskNumber the index of the task
     * @param taskArrayList the list of tasks
     * @return a string representing the task that has been un-marked
     * @throws DukeException the exception to be thrown
     */
    public String unmarkTask(int taskNumber, List<Task> taskArrayList) throws DukeException {
        // Check if user enters a number out of range
        if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
            throw new DukeException("Number out of range!");
        } else {
            return ui.markTaskNotDoneAndPrintOutput(taskNumber);
        }
    }

    /**
     * Delete a task and return a string representing the task that has been deleted
     * @param taskNumber the index of the task
     * @param taskArrayList the list of tasks
     * @return a string representing the task that has been deleted
     * @throws DukeException the exception to be thrown
     */
    public String deleteTask(int taskNumber, List<Task> taskArrayList) throws DukeException {
        if (taskNumber < 0 || taskNumber > taskArrayList.size()) {
            throw new DukeException("Number out of range!");
        } else {
            return ui.markTaskDeletedAndPrintOutput(taskNumber);
        }
    }

    /**
     * Find the matching tasks based on keyword search
     * @param taskArrayList the array list of all the tasks
     * @param keywords the keywords the user typed
     * @return a String representing all the tasks that match the keywords
     */
    public String findMatchingTasks(List<Task> taskArrayList, String[] keywords) {
        int count = 0;
        String outputString = "";
        outputString += "Here are the matching tasks in your list:\n";

        // Partial search with keywords
        for (Task task : taskArrayList) {
            for (String keyword : keywords) {
                if (task.getDescription().contains(keyword)) {
                    outputString += task.toString() + "\n";
                    count += 1;
                    break;
                }
            }
        }
        if (count == 0) {
            return "No matching Tasks Found!";
        }
        return outputString;

    }

    /**
     * Find matching tasks from the keywords
     * @param taskArrayList the list of all the tasks
     * @param keywords the keywords that the user entered
     * @return the string listing all the tasks that match the keywords
     */
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
        try {
            if (wordsArray.length > 1) {
                // Remaining description are the words after the task description
                remainingDescription = Parser.joinString(wordsArray, 1);
                remainingWords = remainingDescription.split(" /by ");
                description = remainingWords[0];
                by = remainingWords[1];
                dateTimeArray = by.split(" ");
                // Cut down a white spacing at the end
                by = dateTimeArray[1];
            }
            // Assert dateTimeArray not null
            assert dateTimeArray != null;
            LocalDate byDate = Parser.createLocalDate(dateTimeArray[0].strip());
            Deadline newDeadline = new Deadline(description, byDate, by);
            taskArrayList.add(newDeadline);
            return ui.printAddedTask(newDeadline);
        } catch (Exception e) {
            return "Invalid format, type 'help2' to see the format again";
        }
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
        try {
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
        } catch (Exception e) {
            return "Invalid format, type 'help2' to see the format again";
        }
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
}
