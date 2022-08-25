import command.Command;
import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Main Duke class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructor.
     * @param filePath Path where disk file should be created.
     */
    public Duke(String filePath) {
        try {
            ui = new UI();
            storage = new Storage(filePath);
            tasks = storage.syncArrayList();
        } catch (DukeException e) {
            System.out.println(e);
            ui.showLoadingError();
        }
    }

    /**
     * Runs Duke program.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
