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
     * @throws DorisException
     */
    public Doris(String filePath) throws DorisException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = Parser.parse(ui.readCommand());
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DorisException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) throws DorisException {
        new Doris("data/tasks.txt").run();
    }
}
