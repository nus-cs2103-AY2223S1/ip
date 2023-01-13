package kirby;

import java.io.FileNotFoundException;
import java.io.IOException;

import kirby.commands.Command;
import kirby.ui.Ui;

/**
 * Kirby class implements the main method.
 */
public class Kirby {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the class Kirby.
     *
     * @param filePath Name of the file path as a string.
     * @param dirPath  Name of the directory path as a string.
     */
    public Kirby(String filePath, String dirPath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath, dirPath);
            // If there is a storage file
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String fullCommand) {
        Command c = Parser.parse(fullCommand, tasks);
        return c.execute(tasks, ui, storage);
    }
}
