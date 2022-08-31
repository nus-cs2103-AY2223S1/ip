package duke.core;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * The main entity for the Duke program.
 *
 * @author Nephelite
 * @version 0.2
 */
public class Duke {
    /**
     * Storage object used to load any previous tasks Duke was tracking in a previous session.
     */
    private final Storage storage;
    /**
     * TaskList object that Duke uses to track all tasks the user inputs as commands.
     */
    private TaskList tasks;
    /**
     * Ui object that allows Duke to communicate with the user.
     */
    private final Ui ui;

    /**
     * Constructor for a Duke object
     *
     * @since 0.1
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showDukeException(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sends a command to Duke to obtain a response
     *
     * @since 0.2
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.inputCommand(input, tasks, ui);
            String response = c.execute(storage);
            System.out.println(response);
            return "Suisei: " + response;
        } catch (DukeException e) {
            return ui.showDukeException(e.getMessage());
        }
    }
}
