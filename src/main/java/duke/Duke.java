package duke;
import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;



/**
 * Represents a Duke class
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Duke {

    /**
     * Represents a storage for texts.
     */
    private final Storage storage;

    /**
     * Represents a list of tasks.
     */
    private final TaskManager taskManager;

    /**
     * Represents a User interface.
     */
    private final Ui ui;

    /**
     * Basic constructor required for Launcher to work.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        this.taskManager = new TaskManager(storage.load());
    }

    /**
     * Represents a constructor method for Duke class
     * @param filepath path to file
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskManager = new TaskManager(storage.load());
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskManager, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method.
     *
     * @param args given arguments
     */
    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();

    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskManager, ui, storage);
        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }
}
