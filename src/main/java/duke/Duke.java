package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.ContentNotFoundException;
import duke.exception.DateNotFoundException;
import duke.exception.InvalidIndexException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.tools.CommandParser;
import duke.tools.Storage;
import duke.tools.Ui;
import javafx.application.Platform;

/**
 * Task Master called Duke to manage those pesky tasks.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private String FILE_PATH = "./data/";

    /**
     * Constructs a duke instance.
     */
    public Duke() {
        this.storage = new Storage(FILE_PATH);
        this.tasks = storage.load();
    }

    /**
     * Returns the relevant String for the GUI.
     * @param input User input.
     * @return String for display on GUI
     */
    public String getResponse(String input) {
        CommandParser keywordParser = new CommandParser(input);
        CommandParser.Commands enumCommand;
        try {
            enumCommand = keywordParser.getCommand();
        } catch (TaskNotFoundException e) {
            return Ui.noTaskExceptionToast(e);
        }
        switch (enumCommand) {
        case BYE:
            Platform.exit();
            return Ui.goodbye();
        case LIST:
            return tasks.list();
        case MARK:
            Task doneTask;
            try {
                doneTask = tasks.markTask(keywordParser.getTaskNo());
                storage.saveTasks(tasks);
            } catch (InvalidIndexException e) {
                return Ui.InvalidIndexExceptionToast(e);
            }
            return Ui.congrats(doneTask);
        case UNMARK:
            Task undoneTask;
            try {
                undoneTask = tasks.unmarkTask(keywordParser.getTaskNo());
                storage.saveTasks(tasks);
            } catch (InvalidIndexException e) {
                return Ui.InvalidIndexExceptionToast(e);
            }
            return Ui.undoneToast(undoneTask);
        case DELETE:
            Task deleted;
            try {
                deleted = tasks.deleteTask(keywordParser.getTaskNo());
                storage.saveTasks(tasks);
            } catch (InvalidIndexException e) {
                return Ui.InvalidIndexExceptionToast(e);
            }
            return Ui.deleteTaskToast(deleted, tasks.size());
        case FIND:
            ArrayList<Task> result = tasks.findTasks(keywordParser.getWord());
            return Ui.foundTaskToast() + "\n"
                    + Ui.printList(result);
        case SORT:
            result = tasks.sortTasks(keywordParser.getSortCriteria());
            return Ui.sortTaskToast(keywordParser.getWord()) + "\n"
                    + Ui.printList(result);
        default:
            return getTaskResponse(input);
        }
    }

    /**
     * Returns a String for GUI to display.
     * @param input User instructions for the system.
     * @return Returns string response to be displayed on GUI.
     */
    private String getTaskResponse(String input) {
        try {
            assert tasks != null : "task list is not null";
            assert storage != null : "storage is not null";
            String response = tasks.addTask(input);
            storage.saveTasks(tasks);
            return response;
        } catch (TaskNotFoundException e) {
            return Ui.noTaskExceptionToast(e);
        } catch (ContentNotFoundException e) {
            return Ui.noContentExceptionToast(e);
        } catch (DateNotFoundException e) {
            return Ui.noDateExceptionToast(e);
        } catch (DateTimeParseException e) {
            return Ui.wrongDateFormatToast(e);
        }
    }
}
