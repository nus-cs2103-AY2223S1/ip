package Duke;

import Duke.exceptions.DukeException;
import Duke.exceptions.InvalidCommandException;
import Duke.exceptions.InvalidDateException;
import Duke.exceptions.InvalidIndexException;
import Duke.exceptions.InvalidSecondaryCommandException;
import Duke.exceptions.InvalidTaskNameException;
import Duke.store.Storage;
import Duke.task.Task;
import Duke.task.TaskDeadline;
import Duke.task.TaskEvent;
import Duke.task.TaskList;
import Duke.task.TaskTodo;
import Duke.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code Duke} class enables users to store and indicated various
 * types of tasking, check and uncheck them, delete them and view a list
 * of all present tasks. It has a command line interface and does not
 * store data from each run.
 */
public class Duke {

    private Storage storage; // Deals with loading tasks from the file and saving tasks in the file.
    private TaskList tasks;  // Stores all the current task created by the user.
    private final Ui ui;     // Deals with making sense of the user command.

    /**
     * Constructor for a duke class.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            ui.fileSuccessfullyLoaded();
            if (tasks.getNumberOfTask() > 0) {
                ui.listTasks(tasks);
                ui.lineDivider();
            }
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Start the duke application.
     */
    public void run() {
        ui.startPrompt();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                String inputString = scanner.nextLine();
                if (inputString.isEmpty()) {
                    continue;
                }
                Parser input = Parser.formatInput(inputString.trim());
                switch (input.getCommand()) {
                case BYE:
                    storage.storeTask(tasks);
                    ui.endPrompt();
                    return;
                case LIST:
                    listTasks();
                    break;
                case CHECK:
                    checkTask(input.getMainData());
                    break;
                case UNCHECK:
                    uncheckTask(input.getMainData());
                    break;
                case DELETE:
                    deleteTask(input.getMainData());
                    break;
                case TODO:
                    addTask(new TaskTodo(input.getMainData()));
                    break;
                case DEADLINE:
                    addTask(new TaskDeadline(input.getMainData(), input.getSecondaryData()));
                    break;
                case EVENT:
                    addTask(new TaskEvent(input.getMainData(), input.getSecondaryData()));
                    break;
                default:
                    break;
                }
            } catch (InvalidCommandException err) {
                ui.showError(String.format("%s is not a valid command", err.getMessage()));
            } catch (InvalidTaskNameException | InvalidIndexException err) {
                ui.showError(err.getMessage());
            } catch (InvalidSecondaryCommandException err) {
                ui.showError(String.format("Please include %s command and the necessary information\n", err.getMessage()));
            } catch (InvalidDateException err) {
                ui.showError(err.getMessage());
                ui.listValidDateFormats();
            } catch (DukeException err) {
                ui.showError(String.format("Unhandled Duke Exception: %s", err.getMessage()));
            } catch (IOException err) {
                ui.showError(String.format("IO Exception: %s", err.getMessage()));
            } catch (Exception err) {
                ui.showError(String.format("Unhandled Exception: %s", err.getMessage()));
            } finally {
                ui.lineDivider();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("|".repeat(60));
        String filepath = "data" + File.separator + "dukeData.txt";
        new Duke(filepath).run();
    }

    /**
     * List all current task in the taskList.
     */
    private void listTasks() {
        ui.listTasks(tasks);
        ui.lineDivider();
    }

    /**
     * Mark a task as done given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    private void checkTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        Task task = tasks.checkTask(Integer.parseInt(index));
        ui.markDone(task.getTaskName());
        ui.listTasks(tasks);
    }

    /**
     * Mark a task as undone given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    private void uncheckTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        Task task = tasks.uncheckTask(Integer.parseInt(index));
        ui.markUndone(task.getTaskName());
        ui.listTasks(tasks);
    }

    /**
     * Delete a task given the index of it in the taskList.
     *
     * @param index an integer representing the index of task in the task list.
     */
    private void deleteTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        Task task = tasks.deleteTask(Integer.parseInt(index));
        ui.deleteTask(task);
        ui.listTasks(tasks);
    }

    /**
     * Add the task given into the taskList.
     *
     * @param <T>  the type of the task we would like to add to the task list.
     * @param task the task we would like to add to the task list.
     */
    private <T extends Task> void addTask(T task) {
        tasks.addTask(task);
        ui.addTask(task);
        ui.listTasks(tasks);
    }
}
