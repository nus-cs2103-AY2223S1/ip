package ted;

import javafx.application.Application;
import ted.command.Command;
import ted.exception.InvalidEncodingException;
import ted.exception.TedException;
import ted.task.TaskList;
import ted.ui.Main;
import ted.ui.UiController;

/**
 * A class that is entry point of program
 */
public class Ted {

    private TaskList tasks;

    private Storage storage;

    private UiController uiController;

    /**
     * To construct a Ted instance
     * @param storage
     */
    public Ted(Storage storage) {
        assert storage != null : "storage should not be null";
        this.storage = storage;
    }

    /**
     * Set UI controller
     * @param uiController
     */
    public void setUiController(UiController uiController) {
        assert uiController != null : "ui should not be null";
        this.uiController = uiController;
    }

    /**
     * Startup the bot by greeting the user and
     * load tasks from storage
     */
    public void startup() {
        this.uiController.showGreeting();
        try {
            this.tasks = storage.loadTasks();
            this.uiController.showTaskLoadSuccess(this.tasks.size());
        } catch (InvalidEncodingException e) {
            this.uiController.showTaskLoadError();
        }
    }

    /**
     * Handle input from user and run the command
     * respectively.
     * @param input
     */
    public void handleInput(String input) {
        try {
            Command command = Parser.parse(input);
            command.run(tasks, uiController, storage);
        } catch (TedException e) {
            this.uiController.showInputError(e);
        }
    }

    /**
     * Entry point of the program
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
