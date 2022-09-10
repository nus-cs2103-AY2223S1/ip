package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.manager.Parser;
import duke.manager.Storage;
import duke.manager.TaskList;

import javafx.application.Application;

/**
 * The Duke program to manage the user's tasks.
 */
public class Duke {
    /** The storage object to handle saving and loading of tasks. */
    private Storage storage;

    /** The list of current tasks. */
    private TaskList tasks;

    /** The friendly name of the Duke bot. */
    public static String BOT_NAME = "DIGITAL DAD";

    /** The friendly emoji associated with the Duke bot. */
    public static String EMOJI = "\uD83E\uDD16";

    /** The greeting to show on booting up the task manager. */
    public static String GREETING = String.format("Hello! I'm %s %s.\nWhat can I do for you?", BOT_NAME, EMOJI);

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns the upcoming deadlines.
     *
     * @return The upcoming deadlines to remind the user of.
     */
    public String getReminders() {
        long maxDaysTo = 7;
        return this.tasks.getTaskReminders(maxDaysTo);
    }

    /**
     * Returns the text to be displayed to the user through the GUI.
     *
     * @param input The input entered by the user.
     * @return The text to be displayed to the user through the GUI.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            String response = c.execute(tasks, storage);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
