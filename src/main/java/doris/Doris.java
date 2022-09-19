package doris;

import command.Command;
import exception.DorisException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Encapsulates a chatbot, Doris, that helps the user keep track of various tasks.
 * Has a Singaporean accent and an attitude.
 *
 * @author Marcus Low
 */
public class Doris {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initialise the Doris chatbot.
     *
     * @param filePath Path to save the local file that stores the list of saved tasks.
     * @throws DorisException If there is a issue loading the file.
     */
    public Doris(String filePath) throws DorisException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(tasks, ui, storage);
        } catch (DorisException e) {
            return ui.showError(e);
        }
    }
}
