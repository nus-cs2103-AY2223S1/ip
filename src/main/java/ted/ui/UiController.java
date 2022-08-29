package ted.ui;

import javafx.application.Platform;
import ted.Ted;
import ted.exception.TedException;
import ted.task.Task;
import ted.task.TaskList;

/**
 * UiController is acts as an controller abstraction
 * between Ted and MainWindow or any GUI node
 *
 * Ted <-> UiController <-> MainWindow
 */
public class UiController {

    private Ted ted;

    private MainWindow mainWindow;

    public void setTed(Ted ted) {
        this.ted = ted;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void output(String message) {
        mainWindow.output(message);
    }

    public void handleInput(String input) {
        this.ted.handleInput(input);
    }

    public void showGreeting() {
        output("Hello! I'm Ted.\nWhat can I do for you?");
    }

    public void outputLine(String message) {
        output(message + "\n");
    }

    /**
     * Show success message when task is added
     * @param tasks all tasks in task list
     */
    public void showAddedTaskSuccess(TaskList tasks) {
        output(String.format("Got it. I've added this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.\n", tasks.last().toString(), tasks.size()));
    }

    /**
     * Show success message when task is deleted
     * @param tasks all task in task list
     * @param deletedTask task that has been deleted
     */
    public void showDeletedTaskSuccess(TaskList tasks, Task deletedTask) {
        output(String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.\n", deletedTask, tasks.size()));
    }

    public void showTaskLoadSuccess(int tasksCount) {
        outputLine(String.format("Loaded %d tasks from saved file.", tasksCount));
    }

    public void showTaskLoadError() {
        outputLine("Error while loading tasks: saved file's encoding incorrect.");
    }

    public void showTaskSavingError(Exception e) {
        outputLine("Error while saving tasks: " + e.getMessage());
    }

    public void showInputError(TedException e) {
        outputLine(e.getMessage());
    }

    public void showUnknownCommandError() {
        outputLine("I'm sorry. I don't understand what that means.");
    }

    /**
     * Exit the ui
     */
    public void exit() {
        outputLine("Bye. Hope to see you again!");
        Platform.exit();
    }
}
