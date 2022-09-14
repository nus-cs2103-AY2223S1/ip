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
 * Runs the Duke chat-bot.
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
        assert current != null : "There is no input. Turtle cannot understand.";
        while (!current.equals("bye")) {
            try {
                String information = Parser.parseCommand(current);
                String[] parts = information.split("\\|");

                switch (parts[0]) {
                case "list":
                    this.ui.printTasks(this.tasks);
                    break;
                case "mark":
                    markInCli(parts,tasks);
                    break;
                case "unmark":
                    unmarkInCli(parts,tasks);
                    break;
                case "delete":
                    deleteInCli(parts,tasks);
                    break;
                case "todo":
                    todoInCli(parts,tasks);
                    break;
                case "deadline":
                    deadlineInCli(parts,tasks, dateTimeFormatter);
                    break;
                case "event":
                    eventInCli(parts,tasks,dateTimeFormatter);
                    break;
                case "find":
                    findInCli(parts,tasks);
                    break;
                case "remind":
                    remindInCli(parts,tasks);
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
     * Marks the task when using Duke CLI.
     * @param parts The user input.
     * @param tasks The current list of task saved.
     * @throws DukeException Handles duke related exceptions.
     */
    public void markInCli(String[] parts, TaskList tasks) throws DukeException {
        checkIndex(parts[1],tasks);
        Task marked = this.tasks.changeTaskStatus(Integer.parseInt(parts[1]), true);
        this.ui.printChangeTaskStatus(marked, true);
    }

    /**
     * Unmarks the task when using Duke CLI.
     * @param parts The user input.
     * @param tasks The current list of task saved.
     * @throws DukeException Handles duke related exceptions.
     */
    public void unmarkInCli(String[] parts, TaskList tasks) throws DukeException {
        checkIndex(parts[1],tasks);
        Task unmarked = this.tasks.changeTaskStatus(Integer.parseInt(parts[1]), false);
        this.ui.printChangeTaskStatus(unmarked, false);
    }

    /**
     * Deletes the task when using Duke CLI.
     * @param parts The user input.
     * @param tasks The current list of task saved.
     * @throws DukeException Handles duke related exceptions.
     */
    public void deleteInCli(String[] parts, TaskList tasks) throws DukeException {
        checkIndex(parts[1],tasks);
        Task deleted = this.tasks.removeTask(Integer.parseInt(parts[1]));
        System.out.println(Integer.parseInt(parts[1]));
        this.ui.printDeleteTask(deleted, this.tasks);
    }

    /**
     * Adds todo task when using Duke CLI.
     * @param parts The user input.
     * @param tasks The current list of task saved.
     * @throws DukeException Handles duke related exceptions.
     */
    public void todoInCli(String[] parts, TaskList tasks) throws DukeException {
        Task todo = new Todo(parts[1]);
        tasks.addTask(todo);
        this.ui.printAddTask(todo, this.tasks);
    }

    /**
     * Adds deadlines task when using Duke CLI.
     * @param parts The user input.
     * @param tasks The current list of task saved.
     * @param dateTimeFormatter date format.
     * @throws DukeException Handles duke related exceptions.
     */
    public void deadlineInCli(String[] parts, TaskList tasks, DateTimeFormatter dateTimeFormatter) throws DukeException {
        Task deadline = new Deadline(
                parts[1],
                LocalDateTime.parse(parts[2], dateTimeFormatter)
        );
        tasks.addTask(deadline);
        this.ui.printAddTask(deadline, this.tasks);
    }

    /**
     * Adds event task when using Duke CLI.
     * @param parts The user input.
     * @param tasks The current list of task saved.
     * @param dateTimeFormatter date format.
     * @throws DukeException Handles duke related exceptions.
     */
    public void eventInCli(String[] parts, TaskList tasks, DateTimeFormatter dateTimeFormatter) throws DukeException {
        Task event = new Event(
                parts[1],
                LocalDateTime.parse(parts[2], dateTimeFormatter),
                LocalDateTime.parse(parts[3], dateTimeFormatter)
        );
        tasks.addTask(event);
        this.ui.printAddTask(event, this.tasks);
    }

    /**
     * Finds task when using Duke CLI.
     * @param parts The user input.
     * @param tasks The current list of task saved.
     * @throws DukeException Handles duke related exceptions.
     */
    public void findInCli(String[] parts, TaskList tasks) throws DukeException {
        ArrayList<Task> targetTasks = this.tasks.findMatchTasks(parts[1]);
        this.ui.printMatchTask(targetTasks);
    }

    /**
     * Reminds unmark deadlines when using Duke CLI.
     * @param parts The user input.
     * @param tasks The current list of task saved.
     * @throws DukeException Handles duke related exceptions.
     */
    public void remindInCli(String[] parts, TaskList tasks) throws DukeException {
        ArrayList<Task> remindTasks = this.tasks.findRemindTasks();
        this.ui.printMatchTask(remindTasks);
    }



    /**
     * Runs the Duke GUI
     *
     * @param input The input text from which to get a response.
     * @return Returns the properly formatted response to display on the GUI.
     */
    public String getResponse(String input) {
        assert input != null : "There is no input. Turtle cannot understand.";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String response = "";
        try {
            String information = Parser.parseCommand(input);
            String[] infoArray = information.split("\\|");
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
                    response = markInGui(infoArray,tasks);
                    break;
                case "unmark":
                    response = unmarkInGui(infoArray,tasks);
                    break;
                case "delete":
                    response = deleteInGui(infoArray,tasks);
                    break;
                case "find":
                    response = findInGui(infoArray, tasks);
                    break;
                case "remind":
                    response = remindInGui(infoArray,tasks);
                    break;
                case "todo":
                    response = todoInGui(infoArray,tasks);
                    break;
                case "deadline":
                    response = deadlineInGui(infoArray,tasks,dateTimeFormatter);
                    break;
                case "event":
                    response = eventInGui(infoArray,tasks,dateTimeFormatter);
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
     * Marks the task when using Duke GUI.
     * @param infoArray The user input.
     * @param tasks The current list of task saved.
     * @return The output to user.
     * @throws DukeException Handles duke related exceptions.
     */
    public String markInGui(String[] infoArray, TaskList tasks) throws DukeException {
        checkIndex(infoArray[1],tasks);
        Task marked = this.tasks.changeTaskStatus(Integer.parseInt(infoArray[1]), true);
        return this.ui.printChangeTaskStatus(marked, true);
    }

    /**
     * Unmarks the task when using Duke GUI.
     * @param infoArray The user input.
     * @param tasks The current list of task saved.
     * @return The output to user.
     * @throws DukeException Handles duke related exceptions.
     */
    public String unmarkInGui(String[] infoArray, TaskList tasks) throws DukeException {
        checkIndex(infoArray[1],tasks);
        Task unmarked = this.tasks.changeTaskStatus(Integer.parseInt(infoArray[1]), false);
        return this.ui.printChangeTaskStatus(unmarked, false);
    }

    /**
     * Deletes task when using Duke GUI.
     * @param infoArray The user input.
     * @param tasks The current list of task saved.
     * @return The output to user.
     * @throws DukeException Handles duke related exceptions.
     */
    public String deleteInGui(String[] infoArray, TaskList tasks) throws DukeException {
        checkIndex(infoArray[1],tasks);
        Task deleted = this.tasks.removeTask(Integer.parseInt(infoArray[1]));
        return this.ui.printDeleteTask(deleted, this.tasks);
    }

    /**
     * Finds task when using Duke GUI.
     * @param infoArray The user input.
     * @param tasks The current list of task saved.
     * @return The output to user.
     * @throws DukeException Handles duke related exceptions.
     */
    public String findInGui(String[] infoArray, TaskList tasks) throws DukeException {
        ArrayList<Task> targetTasks = this.tasks.findMatchTasks(infoArray[1]);
        return this.ui.printMatchTask(targetTasks);
    }

    /**
     * Reminds unmark deadline task when using Duke GUI.
     * @param infoArray The user input.
     * @param tasks The current list of task saved.
     * @return The output to user.
     * @throws DukeException Handles duke related exceptions.
     */
    public String remindInGui(String[] infoArray, TaskList tasks) throws DukeException {
        ArrayList<Task> findTasks = this.tasks.findRemindTasks();
        return this.ui.printMatchTask(findTasks);
    }

    /**
     * Adds todo task when using Duke GUI.
     * @param infoArray The user input.
     * @param tasks The current list of task saved.
     * @return The output to user.
     * @throws DukeException Handles duke related exceptions.
     */
    public String todoInGui(String[] infoArray, TaskList tasks) throws DukeException {
        Task todo = new Todo(infoArray[1]);
        tasks.addTask(todo);
        return this.ui.printAddTask(todo, this.tasks);
    }

    /**
     * Adds deadline task when using Duke GUI.
     * @param infoArray The user input.
     * @param tasks The current list of task saved.
     * @param dateTimeFormatter date format.
     * @return The output to user.
     * @throws DukeException Handles duke related exceptions.
     */
    public String deadlineInGui(String[] infoArray, TaskList tasks, DateTimeFormatter dateTimeFormatter) throws DukeException {
        Task deadline = new Deadline(
                infoArray[1],
                LocalDateTime.parse(infoArray[2], dateTimeFormatter)
        );
        tasks.addTask(deadline);
        return this.ui.printAddTask(deadline, this.tasks);
    }

    /**
     * Adds event task when using Duke GUI.
     * @param infoArray The user input.
     * @param tasks The current list of task saved.
     * @param dateTimeFormatter date format.
     * @return The output to user.
     * @throws DukeException Handles duke related exceptions.
     */
    public String eventInGui(String[] infoArray, TaskList tasks, DateTimeFormatter dateTimeFormatter) throws DukeException {
        Task event = new Event(
                infoArray[1],
                LocalDateTime.parse(infoArray[2], dateTimeFormatter),
                LocalDateTime.parse(infoArray[3], dateTimeFormatter)
        );
        tasks.addTask(event);
        return this.ui.printAddTask(event, this.tasks);
    }

    /**
     * Checks if index input is valid
     * @param inputIndex The user input index
     * @param tasks The current tasks
     * @throws DukeException Handles when user index is invalid
     */
    public static void checkIndex(String inputIndex, TaskList tasks) throws DukeException {
        int index = Integer.parseInt(inputIndex);
        if (index <= 0 || index > tasks.getNumberOfTasks()) {
            throw new DukeException("I'm sorry invalid index!");
        }
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