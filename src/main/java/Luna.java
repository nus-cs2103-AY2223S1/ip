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

    /**
     * Initialises Luna by setting up the UI, Storage and TaskList.
     *
     * @param filePath Path to the file containing saved tasks.
     */
    public Luna(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(ui));
    }

    /**
     * Starts the chatbot by reading commands from and executing them.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LunaException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Luna("data/luna.txt").run();
    }
}
