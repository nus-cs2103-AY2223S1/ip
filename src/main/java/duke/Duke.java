package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.FileCorruptedException;
import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.Error;
import duke.util.Parser;
import duke.util.Response;
import duke.util.Storage;
import javafx.application.Platform;

/**
 * Represents the Duke application.
 *
 * @author njxue
 * @version v0.1
 */
public class Duke {
    /** Ui object which prints the contents in the duke dialog. */
    private final Ui ui;

    /** Storage object which loads and saves the list of tasks. */
    private final Storage storage;

    /** TaskList object containing user's list of tasks. */
    private TaskList tasks;

    /**
     * Creates a new Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("storage/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | FileCorruptedException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses the user's input and returns the corresponding response from the duke chat-bot. If the user inputs an
     * application terminating command (eg. the bye command), the application window closes.
     *
     * @param input Full user input.
     * @return Response from the duke chat-bot.
     */
    public Response getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.isExit()) {
                Platform.exit();
            }
            return command.execute(tasks, ui, storage);
        } catch (DukeException de) {
            return new Error(de.getMessage());
        }
    }

    /**
     * Returns the greeting message from duke.
     *
     * @return Duke greeting message.
     */
    public String getGreetingMessage() {
        return ui.greetingMessage();
    }
}
