package duke;

import java.util.Scanner;
import javafx.application.Application;

import duke.exception.DukeException;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main driver class for the Duke / MumBot programme.
 *
 * @author Kang Wei
 */
public class Duke {

    /**
     * Stores all the tasks of the user.
     */
    private TaskList tasks;

    private Storage storage;

    /**
     * Loads a previously stored list of tasks if present - if not,
     * creates a new file.
     */
    public void loadFile() throws DukeException {
        // Handling of the .txt file containing the list of tasks.
        String filePath = "/Users/kw/Library/Mobile Documents/com~apple~CloudDocs/MODS/"
                + "cs2103/projects/ip/data/duke.txt";
        storage = new Storage(filePath);
        tasks = storage.getTasks();
        assert tasks != null: "tasks should not be null";
    }

    /**
     * Gets MumBot's response to an input by the user.
     *
     * @param input The input of the user.
     * @return The response of MumBot.
     * @throws DukeException Throws a DukeException.
     */
    public String getResponse(String input) throws DukeException {
        return Parser.settleInput(input, tasks);
    }

    /**
     * Returns a message that should be sent to the GUI whenever the
     * programme starts.
     *
     * @return The welcome message.  */
    static public String getWelcomeMsg() {
        return "MumBot: Hi dear, welcome to MumBot! You are precious <3";
    }

    /**
     * Saves the current list of tasks and closes the programme.
     */
    private void exit() throws DukeException {
        storage.save();
        System.exit(0);
    }
}

