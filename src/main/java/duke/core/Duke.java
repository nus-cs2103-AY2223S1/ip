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
     * Most recent command duke has received
     */
    private Command latestCommand;

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
     * @since 0.3
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.inputCommand(input, tasks, ui);
            String response = c.execute(storage);
            latestCommand = c;
            return "Suisei:\n" + response;
        } catch (DukeException e) {
            return ui.showDukeException(e.getMessage());
        }
    }

    /**
     * Checks for if the most recent command was an exit command
     *
     * @return True if the most recent command was an exit command. Otherwise, false.
     * @since 0.3
     */
    public boolean isMostRecentCommandExit() {
        if (latestCommand == null) {
            return false;
        }
        return latestCommand.isExit();
    }

    /**
     * Returns Duke's greetings
     *
     * @return Duke's greetings as a String
     * @since 0.3
     */
    public String greet() {
        return ui.greet();
    }
}
