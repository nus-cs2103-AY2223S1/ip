package myduke;

import java.io.File;

import gui.Launcher;

import command.Command;
import exception.DukeException;

/**
 * This class represents a chat bot.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    /**
     * Constructor for duke.
     *
     * @param filePath file path of the storage file.
     */
    public Duke(String filePath) {
        File file = new File(filePath);
        storage = new Storage(file);
        tasklist = new TaskList();
        storage.loadFromFile(tasklist);
        ui = new Ui(tasklist, storage);
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c instanceof Command;
            String response = c.execute(tasklist, ui, storage);
            assert !response.equals("");
            return response;
        } catch (DukeException e) {
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
