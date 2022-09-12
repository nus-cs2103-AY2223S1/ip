package duke;

import duke.exception.DukeDateTimeException;
import duke.exception.DukeException;
import duke.exception.DukeIndexOutOfBoundsException;
import duke.exception.DukeInvalidException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A Duke object which load the SoCCat,  a Personal Assistant Chatbot that helps a person to keep track of various
 * things. The task list will be saved whenever there are changes, and will be loaded the next time the bot starts up.
 */
public class Duke {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        tasks = new TaskList(storage.loadFromDisk());
    }

    public static void main(String[] args) {
        new Duke().start();
    }
    
    private void start() {
        ui.printWelcomeMessage();
//            repeatUntilQuit();
        exit();
    }

    private void exit() {
        ui.printGoodbyeMessage();
    }

    private String mark(String[] input) throws DukeException {
        parser.checkValidArgLength(input);
        try {
            int taskIndex = parser.getTaskIndex(input);
            Task markedTask = tasks.getTask(taskIndex);
            markedTask.markAsDone();
            storage.saveToDisk(tasks.getTaskList());
            return ui.printTaskMarked(markedTask);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private String unmark(String[] input) throws DukeException {
        parser.checkValidArgLength(input);
        try {
            int taskIndex = parser.getTaskIndex(input);
            Task unmarkedTask = tasks.getTask(taskIndex);
            unmarkedTask.unmarkAsNotDone();
            storage.saveToDisk(tasks.getTaskList());
            return ui.printTaskUnmarked(unmarkedTask);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private String createToDo(String[] input) throws DukeException {
        parser.checkValidArgLength(input);
        try {
            ToDo task = new ToDo(input[1]);
            tasks.addTask(task);
            storage.saveToDisk(tasks.getTaskList());
            return ui.printTaskAdded(task, tasks.getSize());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private String createDeadline(String[] input) throws DukeException {
        parser.checkValidArgLength(input);
        try {
            String[] taskDetails = input[1].split(" /by ", 2);
            String tasking = taskDetails[0];
            String deadline = taskDetails[1];
            LocalDateTime dateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);
            Deadline task = new Deadline(tasking, dateTime);
            tasks.addTask(task);
            storage.saveToDisk(tasks.getTaskList());
            return ui.printTaskAdded(task, tasks.getSize());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        } catch (DateTimeException ex) {
            throw new DukeDateTimeException();
        }
    }

    private String createEvent(String[] input) throws DukeException {
        parser.checkValidArgLength(input);
        try {
            String[] taskDetails = input[1].split(" /at ", 2);
            String tasking = taskDetails[0];
            String eventTime = taskDetails[1];
            LocalDateTime dateTime = LocalDateTime.parse(eventTime, DATE_TIME_FORMATTER);
            Event task = new Event(tasking, dateTime);
            tasks.addTask(task);
            storage.saveToDisk(tasks.getTaskList());
            return ui.printTaskAdded(task, tasks.getSize());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        } catch (DateTimeException ex) {
            throw new DukeDateTimeException();
        }
    }

    private String deleteTask(String[] input) throws DukeException {
        parser.checkValidArgLength(input);
        try {
            int taskIndex = parser.getTaskIndex(input);
            Task deletedTask = tasks.deleteTask(taskIndex);
            storage.saveToDisk(tasks.getTaskList());
            return ui.printTaskDeleted(deletedTask, tasks.getSize());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(tasks.getSize());
        }
    }

    private String findTask(String[] input) throws DukeException {
        parser.checkValidArgLength(input);
        String searchTerm = input[1];
        ArrayList<Task> results = tasks.find(searchTerm);
        return ui.printMatchingTasks(results);
    }

    public String getResponse(String input) {
            String[] words = parser.parseInput(input);
            String keyword = parser.getKeyword(input);

            try {
                switch (keyword) {
                    case "bye":
                        return ui.printGoodbyeMessage();
                    case "list":
                        return ui.printAllTasks(tasks.getTaskList());
                    case "todo":
                        return createToDo(words);
                    case "deadline":
                        return createDeadline(words);
                    case "event":
                        return createEvent(words);
                    case "mark":
                        return mark(words);
                    case "unmark":
                        return unmark(words);
                    case "delete":
                        return deleteTask(words);
                    case "find":
                        return findTask(words);
                    default:
                        throw new DukeInvalidException();
                }
            } catch (DukeException ex) {
                return ex.getMessage();
            } 
    }
}
