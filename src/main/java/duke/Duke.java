package duke;

import duke.exception.DukeDateTimeException;
import duke.exception.DukeException;
import duke.exception.DukeIndexOutOfBoundsException;
import duke.exception.DukeInvalidException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * A Duke object which load the SoCCat,  a Personal Assistant Chatbot that helps a person to keep track of various
 * things. The task list will be saved whenever there are changes, and will be loaded the next time the bot starts up.
 */
public class Duke extends Application {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Creates a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().start();
    }
    private void start() {
        ui.printWelcomeMessage();
        try {
            tasks = new TaskList(storage.loadFromDisk());
            repeatUntilQuit();
            exit();
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void exit() {
        ui.printGoodbyeMessage();
    }

    private void repeatUntilQuit() {
        while (true) {
            String input = ui.getUserInput();
            String[] words = parser.parseInput(input);
            String keyword = parser.getKeyword(input);

            try {
                switch (keyword) {
                    case "bye":
                        return;
                    case "list":
                        ui.printAllTasks(tasks.getTaskList());
                        break;
                    case "todo":
                        createToDo(words);
                        break;
                    case "deadline":
                        createDeadline(words);
                        break;
                    case "event":
                        createEvent(words);
                        break;
                    case "mark":
                        mark(words);
                        break;
                    case "unmark":
                        unmark(words);
                        break;
                    case "delete":
                        deleteTask(words);
                        break;
                    case "find":
                        findTask(words);
                        break;
                    default:
                        throw new DukeInvalidException();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void mark(String[] input) throws DukeException {
        parser.checkArg(input);
        try {
            int taskIndex = parser.getTaskIndex(input);
            Task markedTask = tasks.getTask(taskIndex);
            markedTask.markAsDone();
            ui.printTaskMarked(markedTask);
            storage.saveToDisk(tasks.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private void unmark(String[] input) throws DukeException {
        parser.checkArg(input);
        try {
            int taskIndex = parser.getTaskIndex(input);
            Task unmarkedTask = tasks.getTask(taskIndex);
            unmarkedTask.unmarkAsNotDone();
            ui.printTaskUnmarked(unmarkedTask);
            storage.saveToDisk(tasks.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private void createToDo(String[] input) throws DukeException {
        parser.checkArg(input);
        try {
            ToDo task = new ToDo(input[1]);
            tasks.addTask(task);
            ui.printTaskAdded(task, tasks.getSize());
            storage.saveToDisk(tasks.getTaskList());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private void createDeadline(String[] input) throws DukeException {
        parser.checkArg(input);
        try {
            String[] taskDetails = input[1].split(" /by ", 2);
            String tasking = taskDetails[0];
            String deadline = taskDetails[1];
            LocalDateTime dateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);
            Deadline task = new Deadline(tasking, dateTime);
            tasks.addTask(task);
            ui.printTaskAdded(task, tasks.getSize());
            storage.saveToDisk(tasks.getTaskList());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        } catch (DateTimeException ex) {
            throw new DukeDateTimeException();
        }
    }

    private void createEvent(String[] input) throws DukeException {
        parser.checkArg(input);
        try {
            String[] taskDetails = input[1].split(" /at ", 2);
            String tasking = taskDetails[0];
            String eventTime = taskDetails[1];
            LocalDateTime dateTime = LocalDateTime.parse(eventTime, DATE_TIME_FORMATTER);
            Event task = new Event(tasking, dateTime);
            tasks.addTask(task);
            ui.printTaskAdded(task, tasks.getSize());
            storage.saveToDisk(tasks.getTaskList());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        } catch (DateTimeException ex) {
            throw new DukeDateTimeException();
        }
    }

    private void deleteTask(String[] input) throws DukeException {
        parser.checkArg(input);
        try {
            int taskIndex = parser.getTaskIndex(input);
            Task deletedTask = tasks.deleteTask(taskIndex);
            ui.printTaskDeleted(deletedTask, tasks.getSize());
            storage.saveToDisk(tasks.getTaskList());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private void findTask(String[] input) throws DukeException {
        parser.checkArg(input);
        String searchTerm = input[1];
        ArrayList<Task> results = tasks.find(searchTerm);
        ui.printMatchingTasks(results);
    }
}
