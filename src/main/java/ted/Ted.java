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

    private UiController ui;

    /**
     * To construct a Ted instance
     * @param storage
     */
    public Ted(Storage storage) {
        this.storage = storage;
    }

    public void setUi(UiController ui) {
        this.ui = ui;
    }

    /**
     * Startup the bot by greeting the user and
     * load tasks from storage
     */
    public void startup() {
        this.ui.showGreeting();
        try {
            this.tasks = storage.loadTasks();
            this.ui.showTaskLoadSuccess(this.tasks.size());
        } catch (InvalidEncodingException e) {
            this.ui.showTaskLoadError();
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
            command.run(tasks, ui, storage);
        } catch (TedException e) {
            this.ui.showInputError(e);
        }
    }

    public String getResponse(String input) {
        return "Test";
    }

    /**
     * Entry point of the program
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
