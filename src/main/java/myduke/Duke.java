package myduke;

import java.time.format.DateTimeParseException;
import java.io.File;

import gui.Launcher;

import exception.DukeException;

/**
 * This class represents a chat bot.
 */
public class Duke {
    private Ui ui;

    /**
     * Constructor for duke.
     *
     * @param filePath absolute file path of the storage file.
     */
    public Duke(String filePath) {
        File file = new File(filePath);
        Storage storage = new Storage(file);
        TaskList tasks = new TaskList();
        storage.loadFromFile(tasks);
        ui = new Ui(tasks, storage);
    }

    public String getResponse(String input) {
        try {
            return ui.response(input);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return e.getMessage();
        }
    }

    public String getIntro() {
        return ui.welcome();
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
