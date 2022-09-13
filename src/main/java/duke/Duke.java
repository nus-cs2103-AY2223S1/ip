package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.utilities.DukeException;
import duke.utilities.Parser;
import duke.utilities.Ui;

/**
 * The Main driver class of the Duke Application.
 */
public class Duke {
    /** The Storage object that handles loading and saving tasks. */
    private Storage storage;

    /** The TaskList object that manages the tasks. */
    private TaskList taskList;

    /** The UI object that handles user interface. */
    private Ui ui;

    /**
     * Constructs a Duke Instance.
     *
     * @param filePath The location to store the saved tasks to.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(this.storage.readTasksFromStorage());
        } catch (IOException e) {
            ui.printIoException(e);
            this.taskList = new TaskList();
        } catch (DukeException e) {
            ui.printDukeException(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Drives the Duke CLI program.
     */
    public void run() {
        this.ui.printDukeOpening();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String currentLine = this.ui.nextLine();

        while (!currentLine.equals("bye")) {
            try {
                String information = Parser.parseCommand(currentLine);
                String[] infoArray = information.split("\\|");

                switch (infoArray[0]) {
                case "list":
                    this.ui.printTasks(this.taskList);
                    break;
                case "mark":
                    Task marked = this.taskList.changeTaskStatus(Integer.parseInt(infoArray[1]), true);
                    this.ui.printChangeTaskStatus(marked, true);
                    break;
                case "unmark":
                    Task unmarked = this.taskList.changeTaskStatus(Integer.parseInt(infoArray[1]), false);
                    this.ui.printChangeTaskStatus(unmarked, false);
                    break;
                case "delete":
                    Task deleted = this.taskList.deleteTask(Integer.parseInt(infoArray[1]));
                    this.ui.printDeleteTask(deleted, this.taskList);
                    break;
                case "find":
                    ArrayList<Task> targetTasks = this.taskList.findMatchingTasks(infoArray[1]);
                    this.ui.printMatchingTasks(targetTasks);
                    break;
                case "todo":
                    Task todo = new Todo(infoArray[1]);
                    taskList.addTask(todo);
                    this.ui.printAddTask(todo, this.taskList);
                    break;
                case "deadline":
                    Task deadline = new Deadline(
                            infoArray[1],
                            LocalDateTime.parse(infoArray[2], dateTimeFormatter)
                    );
                    taskList.addTask(deadline);
                    this.ui.printAddTask(deadline, this.taskList);
                    break;
                case "event":
                    Task event = new Event(
                            infoArray[1],
                            LocalDateTime.parse(infoArray[2], dateTimeFormatter),
                            LocalDateTime.parse(infoArray[3], dateTimeFormatter)
                    );
                    taskList.addTask(event);
                    this.ui.printAddTask(event, this.taskList);
                    break;
                default:
                    throw new DukeException("I'm sorry but I don't know what that means!");
                }
            } catch (DukeException e) {
                this.ui.printDukeException(e);
            } catch (DateTimeParseException e) {
                this.ui.printDateTimeParseException();
            }

            currentLine = this.ui.nextLine();
        }

        try {
            this.storage.writeTasksToStorage(this.taskList);
        } catch (IOException e) {
            this.ui.printIoException(e);
        }

        this.ui.printDukeClosing();
        this.ui.closeScanner();
        System.exit(0);
    }

    /**
     * Gets responses from the Duke program to display on the GUI.
     *
     * @param input The input text from which to get a response.
     * @return Returns the properly formatted response to display on the GUI.
     */
    public String getResponse(String input) {
        assert input != null : "OOPS! Something went wrong and Duke is not reading inputs!";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String response = "";
        try {
            String information = Parser.parseCommand(input);
            String[] infoArray = information.split("\\|");
            String command = infoArray[0];

            switch (command) {
            case "hello":
                response = this.ui.getStringDukeOpening();
                break;
            case "bye":
                response = this.ui.getStringDukeClosing();
                this.storage.writeTasksToStorage(this.taskList);
                break;
            case "list":
                response = this.ui.getStringTasks(this.taskList);
                break;
            case "mark":
                Task marked = this.taskList.changeTaskStatus(Integer.parseInt(infoArray[1]), true);
                response = this.ui.getStringChangeTaskStatus(marked, true);
                break;
            case "unmark":
                Task unmarked = this.taskList.changeTaskStatus(Integer.parseInt(infoArray[1]), false);
                response = this.ui.getStringChangeTaskStatus(unmarked, false);
                break;
            case "delete":
                Task deleted = this.taskList.deleteTask(Integer.parseInt(infoArray[1]));
                response = this.ui.getStringDeleteTask(deleted, this.taskList);
                break;
            case "find":
                ArrayList<Task> targetTasks = this.taskList.findMatchingTasks(infoArray[1]);
                response = this.ui.getStringMatchingTasks(targetTasks);
                break;
            case "remind":
                response = this.ui.getReminders(taskList, infoArray[1]);
                break;
            case "todo":
                Task todo = new Todo(infoArray[1]);
                taskList.addTask(todo);
                response = this.ui.getStringAddTask(todo, this.taskList);
                break;
            case "deadline":
                LocalDateTime by = LocalDateTime.parse(infoArray[2], dateTimeFormatter);
                Task deadline = new Deadline(infoArray[1], by);
                taskList.addTask(deadline);
                response = this.ui.getStringAddTask(deadline, this.taskList);
                break;
            case "event":
                LocalDateTime from = LocalDateTime.parse(infoArray[2], dateTimeFormatter);
                LocalDateTime to = LocalDateTime.parse(infoArray[3], dateTimeFormatter);
                Task event = new Event(infoArray[1], from, to);
                taskList.addTask(event);
                response = this.ui.getStringAddTask(event, this.taskList);
                break;
            default:
                throw new DukeException("I'm sorry but I don't know what that means!");
            }
        } catch (DukeException e) {
            response = this.ui.getStringDukeException(e);
        } catch (DateTimeParseException e) {
            response = this.ui.getStringDateTimeParseException();
        } catch (IOException e) {
            response = this.ui.getStringIoException(e);
        } catch (Exception e) {
            response = this.ui.getStringUnexpectedException();
        }

        return response;
    }


    /**
     * Runs the Duke Application in CLI mode.
     *
     * @param args Command line arguments that we can pass to the main function.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
