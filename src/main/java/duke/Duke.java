package duke;

import duke.commands.Command;
import duke.others.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Represents a bot that will carry out different command with user input.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Duke instance,
     * and loads the content of the local file into storage.
     *
     * @param filePath path of the local path.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Main of the whole programme.
     *
     * @param args Arguments.
     */
/*    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }*/

    /**
     * Runs the bot by taking in user input,
     * which is then parsed into commands and executed.
     */
/*    public void run() {

        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isRunning = c.isRunning();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }*/

    /**
     * Returns a string of Duke's response to the given input.
     *
     * @param input User input.
     * @return A string of responses.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
