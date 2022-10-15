package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.TextUi;
import duke.ui.Ui;

/**
 * The main class which handles the overarching logic of the program.
 */
public class Duke {

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private boolean isExit;

    public Duke(String filePath, Ui ui, String aliasPath) {
        this.ui = ui;
        this.storage = new Storage(filePath, aliasPath);
        this.tasks = new TaskList(storage);
        this.parser = new Parser(storage);

        this.ui.showWelcome();
    }

    /**
     * Handles the user-provided input and evaluate it.
     *
     * @param userInput The user input string.
     */
    public void handleUserInput(String userInput) {
        assert !isExit : "Duke should not be exiting.";
        try {
            Command command = parser.parse(userInput);
            command.execute(tasks, ui, storage);
            this.isExit = command.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Indicates whether the application is shutting down.
     *
     * @return A boolean on whether Duke is shutting down
     */
    public boolean isExit() {
        return this.isExit;
    }

    private void run() {
        assert this.ui instanceof TextUi : "`Duke::run` should only be used with a TextUI";
        while (!isExit) {
            handleUserInput(ui.getUserInput());
        }
    }

    /**
     * Runs Duke with an example TextUI.
     * @param args
     */
    public static void main(String[] args) {
        // Using a Text UI
        new Duke("database.txt", new TextUi(), "databaseAlias.txt").run();
    }
}

