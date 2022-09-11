package Duke;

import Duke.exceptions.DukeException;
import Duke.exceptions.InvalidCommandException;
import Duke.exceptions.InvalidDateException;
import Duke.exceptions.InvalidFindException;
import Duke.exceptions.InvalidIndexException;
import Duke.exceptions.InvalidSecondaryCommandException;
import Duke.exceptions.InvalidTaskNameException;
import Duke.store.Storage;
import Duke.task.Task;
import Duke.task.TaskDeadline;
import Duke.task.TaskEvent;
import Duke.task.TaskList;
import Duke.task.TaskTodo;
import Duke.ui.DukeResponses;
import Duke.ui.GuiUi;
import Duke.utils.Utils;

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
    private final DukeResponses dukeResponses = new DukeResponses();
    // Handles how to display the UI.
    private GuiUi guiUi;
    // String to specify location of previous information.
    private static final String filepath = "data" + File.separator + "dukeData.txt";

    /**
     * Load the {@link Duke#storage storage} and {@link Duke#tasks tasklist} for Duke.
     */
    public void load() {
        initialiseStorage();
        loadTaskFromStorageIntoTasks();
        welcomeUser();
    }

    /**
     * Create a {@link Duke#storage storage} based on the specified {@link Duke#filepath}.
     */
    private void initialiseStorage() {
        try {
            storage = new Storage(filepath);
        } catch (DukeException | IOException e) {
            guiUi.displayOutput(dukeResponses.loadFileFailed() + '\n' + e.getMessage());
        }
    }

    /**
     * Load the {@link Duke#tasks tasks} from the {@link Duke#storage storage}.
     */
    private void loadTaskFromStorageIntoTasks() {
        try {
            assert storage != null : "The storage should not be null when loading tasks";
            tasks = new TaskList(storage.load());
            if (tasks.isNotEmpty()) {
                guiUi.displayOutput(dukeResponses.loadTaskSuccessfully() + '\n' + dukeResponses.listTasks(tasks));
            }
        } catch (DukeException e) {
            tasks = new TaskList();
            guiUi.displayOutput(dukeResponses.loadTaskFailed() + '\n' + e.getMessage());
        }
    }

    /**
     * Display a welcome message to the user.
     */
    private void welcomeUser() {
        guiUi.displayOutput(dukeResponses.startPrompt());
    }

    /**
     * Return an output from the input provided by a user.
     *
     * @param inputString a string input from the user.
     * @return a string output from Duke.
     */
    public String receiveInput(String inputString) {
        String response = "";
        try {
            if (inputString.isEmpty()) {
                return "Hmm I did not quite catch that";
            }
            Parser input = Parser.formatInput(inputString.trim());
            switch (input.getCommand()) {
            case BYE:
                terminate();
                return null;
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
                    dukeResponses.listValidInstructions());
        } catch (InvalidTaskNameException | InvalidIndexException | InvalidFindException err) {
            response = err.getMessage();
        } catch (InvalidSecondaryCommandException err) {
            response = String.format("Please include %s command and the necessary information", err.getMessage());
        } catch (InvalidDateException err) {
            response = String.format("%s\n%s", err.getMessage(), dukeResponses.listValidDateFormats());
        } catch (DukeException err) {
            response = String.format("Unhandled Duke Exception: %s", err.getMessage());
        } catch (Exception err) {
            response = String.format("Unhandled Exception: %s", err.getMessage());
        }
        return response;
    }

    /**
     * Terminate the programme upon completion.
     */
    private void terminate() {
        try {
            assert tasks != null : "The tasks should not be null when storing them in storage";
            storage.storeTask(tasks);
            guiUi.displayOutput(dukeResponses.endPrompt());
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
     * List all current task in the taskList.
     */
    private String listTasks() {
        assert tasks != null : "The tasks should not be null when listing them";
        return dukeResponses.listTasks(tasks);
    }

    /**
     * Find all current task in the taskList base on a string.
     */
    private String findTasks(String string) {
        assert tasks != null : "The tasks should not be null when finding tasks";
        return dukeResponses.findTasks(tasks, string);
    }

    /**
     * Mark a task as done given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    private String checkTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        assert tasks != null : "The tasks should not be null when checking tasks";
        Task task = tasks.checkTask(Integer.parseInt(index));
        return dukeResponses.markDone(task.getTaskName()) + "\n" + dukeResponses.listTasks(tasks);
    }

    /**
     * Mark a task as undone given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    private String uncheckTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        assert tasks != null : "The tasks should not be null when unchecking tasks";
        Task task = tasks.uncheckTask(Integer.parseInt(index));
        return dukeResponses.markUndone(task.getTaskName()) + "\n" + dukeResponses.listTasks(tasks);
    }

    /**
     * Delete a task given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    private String deleteTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        assert tasks != null : "The tasks should not be null when deleting tasks";
        Task task = tasks.deleteTask(Integer.parseInt(index));
        return dukeResponses.deleteTask(task) + "\n" + dukeResponses.listTasks(tasks);
    }

    /**
     * Add the task given into the taskList.
     *
     * @param <T>  the type of the task we would like to add to the task list.
     * @param task the task we would like to add to the task list.
     */
    private <T extends Task> String addTask(T task) {
        assert tasks != null : "The tasks should not be null when adding tasks";
        tasks.addTask(task);
        return dukeResponses.addTask(task) + "\n" + dukeResponses.listTasks(tasks);
    }

    /**
     * Set a gui for Duke.
     *
     * @param guiUi a guiUi for Duke.
     */
    public void setGui(GuiUi guiUi) {
        this.guiUi = guiUi;
    }
}
