package duke;

import duke.command.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.utilities.DukeException;
import duke.utilities.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The main class of the Duke chat-bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //@@author DanielLimWeiEn -reused
    /**
     * Constructs a new {@code Duke} with a datafile path.
     *
     * @param filePath The path to the datafile.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (IOException e) {
            ui.printIoException(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chat-bot.
     */
    public void run() {
        this.ui.printDukeOpening();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String current = this.ui.nextLine();

        while (!current.equals("bye")) {
            try {
                String information = Parser.parseCommand(current);
                String[] parts = information.split("\\|");

                switch (parts[0]) {
                case "list":
                    this.ui.printTasks(this.tasks);
                    break;
                case "mark":
                    Task marked = this.tasks.changeTaskStatus(Integer.parseInt(parts[1]), true);
                    this.ui.printChangeTaskStatus(marked, true);
                    break;
                case "unmark":
                    Task unmarked = this.tasks.changeTaskStatus(Integer.parseInt(parts[1]), false);
                    this.ui.printChangeTaskStatus(unmarked, false);
                    break;
                case "delete":
                    Task deleted = this.tasks.removeTask(Integer.parseInt(parts[1]));
                    System.out.println(Integer.parseInt(parts[1]));
                    this.ui.printDeleteTask(deleted, this.tasks);
                    break;
                case "todo":
                    Task todo = new Todo(parts[1]);
                    tasks.addTask(todo);
                    this.ui.printAddTask(todo, this.tasks);
                    break;
                case "deadline":
                    Task deadline = new Deadline(
                            parts[1],
                            LocalDateTime.parse(parts[2], dateTimeFormatter)
                    );
                    tasks.addTask(deadline);
                    this.ui.printAddTask(deadline, this.tasks);
                    break;
                case "event":
                    Task event = new Event(
                            parts[1],
                            LocalDateTime.parse(parts[2], dateTimeFormatter),
                            LocalDateTime.parse(parts[3], dateTimeFormatter)
                    );
                    tasks.addTask(event);
                    this.ui.printAddTask(event, this.tasks);
                    break;
                case "find":
                    ArrayList<Task> targetTasks = this.tasks.findMatchTasks(parts[1]);
                    this.ui.printMatchTask(targetTasks);
                    break;
                default:
                    throw new DukeException("I'm sorry but I don't know what that means!");
                }
            } catch (DukeException e) {
                this.ui.printDukeException(e);
            } catch (DateTimeParseException e) {
                this.ui.printDateTimeParseException();
            }

            current = this.ui.nextLine();
        }

        try {
            this.storage.writeTasksToStorage(this.tasks);
        } catch (IOException e) {
            this.ui.printIoException(e);
        }

        this.ui.printDukeClosing();
        this.ui.closeScanner();
        System.exit(0);
    }
    //@@author

    /**
     * Runs the Duke GUI
     *
     * @param input The input text from which to get a response.
     * @return Returns the properly formatted response to display on the GUI.
     */
    public String getResponse(String input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String response = "";
        try {
            String information = Parser.parseCommand(input);
            String[] infoArray = information.split("\\|");
            System.out.println("/" + infoArray[0]+"/");

            switch (infoArray[0]) {
                case "hello":
                    response = this.ui.printDukeOpening();
                    break;
                case "bye":
                    response = this.ui.printDukeClosing();
                    this.storage.writeTasksToStorage(this.tasks);
                    break;
                case "list":
                    response = this.ui.printTasks(this.tasks);
                    break;
                case "mark":
                    Task marked = this.tasks.changeTaskStatus(Integer.parseInt(infoArray[1]), true);
                    response = this.ui.printChangeTaskStatus(marked, true);
                    break;
                case "unmark":
                    Task unmarked = this.tasks.changeTaskStatus(Integer.parseInt(infoArray[1]), false);
                    response = this.ui.printChangeTaskStatus(unmarked, false);
                    break;
                case "delete":
                    Task deleted = this.tasks.removeTask(Integer.parseInt(infoArray[1]));
                    response = this.ui.printDeleteTask(deleted, this.tasks);
                    break;
                case "find":
                    ArrayList<Task> targetTasks = this.tasks.findMatchTasks(infoArray[1]);
                    response = this.ui.printMatchTask(targetTasks);
                    break;
                case "todo":
                    Task todo = new Todo(infoArray[1]);
                    tasks.addTask(todo);
                    response = this.ui.printAddTask(todo, this.tasks);
                    break;
                case "deadline":
                    Task deadline = new Deadline(
                            infoArray[1],
                            LocalDateTime.parse(infoArray[2], dateTimeFormatter)
                    );
                    tasks.addTask(deadline);
                    response = this.ui.printAddTask(deadline, this.tasks);
                    break;
                case "event":
                    Task event = new Event(
                            infoArray[1],
                            LocalDateTime.parse(infoArray[2], dateTimeFormatter),
                            LocalDateTime.parse(infoArray[3], dateTimeFormatter)
                    );
                    tasks.addTask(event);
                    response = this.ui.printAddTask(event, this.tasks);
                    break;
                default:
                    throw new DukeException("I'm sorry but I don't know what that means!");
            }
        } catch (DukeException e) {
            response = this.ui.printDukeException(e);
        } catch (DateTimeParseException e) {
            response = this.ui.printDateTimeParseException();
        } catch (IOException e) {
            response = this.ui.printIoException(e);
        } catch (Exception e) {
            response = "An unexpected exception has occurred.";
        }

        try {
            this.storage.writeTasksToStorage(this.tasks);
        } catch (IOException e) {
            this.ui.printIoException(e);
        }
        return response;
    }


    /**
     * The main method that is the entry to the Duke Application.
     *
     * @param args The input task file.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}