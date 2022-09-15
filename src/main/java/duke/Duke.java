package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidFindException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidSecondaryCommandException;
import duke.exceptions.InvalidTaskNameException;
import duke.store.Storage;
import duke.task.Task;
import duke.task.TaskDeadline;
import duke.task.TaskEvent;
import duke.task.TaskList;
import duke.task.TaskTodo;
import duke.ui.GuiUi;
import duke.ui.NekoResponses;
import duke.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The {@code Duke} class enables users to store and indicated various
 * types of tasking, check and uncheck them, delete them and view a list
 * of all present tasks. It has a command line interface and does not
 * store data from each run.
 */
public class Duke {

    // Deals with loading tasks from the file and saving tasks in the file.
    private Storage storage;
    // Stores all the current task created by the user.
    private TaskList tasks;
    // Handles how to respond to user inputs.
    private final NekoResponses nekoResponses = new NekoResponses();
    // Handles how to display the UI.
    private GuiUi guiUi;
    // String to specify location of previous information.
    private static final String FILEPATH = "data" + File.separator + "dukeData.txt";

    /**
     * Loads the {@link Duke#storage storage} and {@link Duke#tasks task list} for Duke.
     */
    public void load() {
        initialiseStorage();
        loadTaskFromStorageIntoTasks();
        welcomeUser();
    }

    /**
     * Creates a {@link Duke#storage storage} based on the specified {@link Duke#FILEPATH}.
     */
    private void initialiseStorage() {
        try {
            storage = new Storage(FILEPATH);
        } catch (DukeException | IOException e) {
            guiUi.displayOutput(String.format("%s\n%s", nekoResponses.loadFileFailed(), e.getMessage()));
        }
    }

    /**
     * Loads the {@link Duke#tasks tasks} from the {@link Duke#storage storage}.
     */
    private void loadTaskFromStorageIntoTasks() {
        try {
            assert storage != null : "The storage should not be null when loading tasks";
            tasks = new TaskList(storage.load());
            if (tasks.isNotEmpty()) {
                guiUi.displayOutput(String.format("%s\n%s", nekoResponses.loadTaskSuccessfully(),
                        nekoResponses.listTasks(tasks)));
            }
        } catch (DukeException e) {
            tasks = new TaskList();
            guiUi.displayOutput(String.format("%s\n%s", nekoResponses.loadTaskFailed(), e.getMessage()));
        }
    }

    /**
     * Displays a welcome message to the user.
     */
    private void welcomeUser() {
        guiUi.displayOutput(nekoResponses.startPrompt());
    }

    /**
     * Returns an output from the input provided by a user.
     *
     * @param inputString a string input from the user.
     * @return a string output from Duke.
     */
    public String receiveInput(String inputString) {
        String response = "";
        try {
            assert !inputString.isBlank();
            Parser input = Parser.formatInput(inputString.trim());
            switch (input.getCommand()) {
            case BYE:
                terminate();
                break;
            case HELP:
                response = showHelp();
                break;
            case LIST:
                response = listTasks();
                break;
            case FIND:
                response = findTasks(input.getMainData());
                break;
            case CHECK:
                response = checkTask(input.getMainData());
                break;
            case UNCHECK:
                response = uncheckTask(input.getMainData());
                break;
            case DELETE:
                response = deleteTask(input.getMainData());
                break;
            case TODO:
                response = addTask(new TaskTodo(input.getMainData()));
                break;
            case DEADLINE:
                response = addTask(new TaskDeadline(input.getMainData(), input.getSecondaryData()));
                break;
            case EVENT:
                response = addTask(new TaskEvent(input.getMainData(), input.getSecondaryData()));
                break;
            default:
                break;
            }
        } catch (InvalidCommandException err) {
            response = String.format("%s is not a valid command\n%s", err.getMessage(),
                    nekoResponses.hintUserOfHelpCommand());
        } catch (InvalidTaskNameException | InvalidIndexException | InvalidFindException err) {
            response = err.getMessage();
        } catch (InvalidSecondaryCommandException err) {
            response = String.format("Please include %s command and the necessary information", err.getMessage());
        } catch (InvalidDateException err) {
            response = String.format("%s\n%s", err.getMessage(), nekoResponses.listValidDateFormats());
        } catch (DukeException err) {
            response = String.format("Unhandled Duke Exception: %s", err.getMessage());
        } catch (Exception err) {
            response = String.format("Unhandled Exception: %s", err.getMessage());
        }
        return response;
    }

    /**
     * Terminates the programme upon completion.
     */
    private void terminate() {
        try {
            assert tasks != null : "The tasks should not be null when storing them in storage";
            storage.storeTask(tasks);
            guiUi.displayOutput(nekoResponses.endPrompt());
            TimerTask exitApp = new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            };
            new Timer().schedule(exitApp, new Date(System.currentTimeMillis() + 1000));
        } catch (IOException err) {
            String response = String.format("IO Exception: %s", err.getMessage());
            guiUi.displayOutput(response);
        }
    }

    /**
     * Shows help that serves as instructions of how to use Duke.
     *
     * @return a string containing the response of the help message.
     */
    private String showHelp() {
        return nekoResponses.showHelp();
    }

    /**
     * Lists all current task in the task list.
     *
     * @return a string containing the response the list of tasks.
     */
    private String listTasks() {
        assert tasks != null : "The tasks should not be null when listing them";
        return nekoResponses.listTasks(tasks);
    }

    /**
     * Finds all current task in the task list base on a string.
     *
     * @param searchString a string containing the text to be searched for in the task list.
     * @return a string containing the response with a list of tasks matching the search string.
     */
    private String findTasks(String searchString) {
        assert tasks != null : "The tasks should not be null when finding tasks";
        return nekoResponses.findTasks(tasks, searchString);
    }

    /**
     * Marks a task as done given the index of it in the task list.
     *
     * @param index an integer representing the index of task in the task list.
     * @return a string containing the response after checking a tasks.
     */
    private String checkTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        assert tasks != null : "The tasks should not be null when checking tasks";
        Task task = tasks.checkTask(Integer.parseInt(index));
        return String.format("%s\n%s", nekoResponses.markDone(task.getTaskName()), nekoResponses.listTasks(tasks));
    }

    /**
     * Marks a task as undone given the index of it in the task list.
     *
     * @param index an integer representing the index of task in the task list.
     * @return a string containing the response after unchecking a tasks.
     */
    private String uncheckTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        assert tasks != null : "The tasks should not be null when unchecking tasks";
        Task task = tasks.uncheckTask(Integer.parseInt(index));
        return String.format("%s\n%s", nekoResponses.markUndone(task.getTaskName()), nekoResponses.listTasks(tasks));
    }

    /**
     * Deletes a task given the index of it in the task list.
     *
     * @param index an integer representing the index of task in the task list.
     * @return a string containing the response after deleting a task.
     */
    private String deleteTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        assert tasks != null : "The tasks should not be null when deleting tasks";
        Task task = tasks.deleteTask(Integer.parseInt(index));
        return String.format("%s\n%s", nekoResponses.deleteTask(task), nekoResponses.listTasks(tasks));
    }

    /**
     * Adds the task given into the task list.
     *
     * @param <T>  the type of the task we would like to add to the task list.
     * @param task the task we would like to add to the task list.
     * @return a string containing the response after adding a task to the task list.
     */
    private <T extends Task> String addTask(T task) {
        assert tasks != null : "The tasks should not be null when adding tasks";
        tasks.addTask(task);
        return String.format("%s\n%s", nekoResponses.addTask(task), nekoResponses.listTasks(tasks));
    }

    /**
     * Sets a gui for Duke.
     *
     * @param guiUi a guiUi for Duke.
     */
    public void setGui(GuiUi guiUi) {
        this.guiUi = guiUi;
    }
}
