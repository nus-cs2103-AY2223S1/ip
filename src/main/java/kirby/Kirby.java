package kirby;

import java.io.FileNotFoundException;
import java.io.IOException;

import kirby.commands.Command;
import kirby.exceptions.KirbyInvalidCommandException;
import kirby.exceptions.KirbyMissingArgumentException;
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
     * @throws FileNotFoundException If the file mentioned is not found.
     * @throws IOException           If there is an improper input error.
     */
    public Kirby(String filePath, String dirPath) throws FileNotFoundException, IOException {
        ui = new Ui();
        try {
            storage = new Storage(filePath, dirPath);
            // if there is a storage file
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String fullCommand) throws KirbyInvalidCommandException, KirbyMissingArgumentException {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (KirbyInvalidCommandException | KirbyMissingArgumentException e) {
            String error = e.getMessage();
            return error;
        }
    }
}
