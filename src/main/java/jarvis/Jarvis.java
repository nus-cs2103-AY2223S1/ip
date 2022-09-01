package jarvis;

import jarvis.command.Command;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Jarvis --- Task manager.
 */
public class Jarvis {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor.
     */
    public Jarvis() {
        this.storage = new Storage(System.getProperty("user.dir") + "/data/tasks.txt");
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (JarvisException e) {
            this.ui.showMessage("Error loading tasks. Creating new task list...");
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseUserCommand(input);
            return command.execute(tasks, storage);
        } catch (JarvisException e) {
            return e.getMessage();
        }
    }
}
