import command.Command;
import exception.LunaException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a personal assistant chatbot, Luna, that helps the user keep track of
 * various tasks and events.
 * Nature and celestial themed.
 *
 * @author fannyjian
 */
public class Luna {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initialises Luna by setting up the UI, Storage and TaskList.
     *
     * @param filePath Path to the file containing saved tasks.
     */
    public Luna(String filePath) {
        parser = new Parser();
        ui = new Ui(parser);
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(ui));
    }

    public String getWelcomeMessage() {
        return this.ui.showWelcome();
    }

    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(tasks, ui, storage);
        } catch (LunaException e) {
            return ui.showError(e);
        }
    }

}
