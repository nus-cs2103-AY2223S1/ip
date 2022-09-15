package duke;
import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * Represents a Duke class.
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
     * Constructor method for Duke class.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        this.taskManager = new TaskManager(storage.load());
    }

    /**
     * Represents a constructor method for Duke class.
     * @param filepath Path to file.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskManager = new TaskManager(storage.load());
    }

    /**
     * Gets the Ui in the duke object.
     * @return Ui object.
     */
    public Ui getUi() {
        return this.ui;
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
     * @param args Given arguments.
     */
    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();

    }


    /**
     * Gets response of Duke when given a string message.
     * @param input Message passed to Duke.
     * @return Pair consisting of a String and a Boolean.
     */
    Pair<String, Boolean> getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return new Pair<>(c.execute(taskManager, ui, storage), c.isExit());
        } catch (DukeException | IOException e) {
            return new Pair<>(e.toString(), false);
        }
    }
}
