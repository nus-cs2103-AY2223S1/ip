import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Duke implements the Duke bot, functions as a simple todo-list.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic of Duke.
     * Handles Welcome and Goodbye messages, as well as all commands.
     */
    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLineBreak();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            finally {
                ui.showLineBreak();
            }
        }
    }
    
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
