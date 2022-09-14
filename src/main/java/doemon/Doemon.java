package doemon;

import doemon.command.Command;
import doemon.exception.DoemonException;
import doemon.exception.TaskDataException;
import doemon.parser.Parser;
import doemon.storage.Storage;
import doemon.task.TaskList;
import doemon.ui.Ui;

/**
 * Doemon chat bot and task manager.
 */
public class Doemon {

    /** The file path of the file where tasks will be saved to. */
    private static final String TASK_FILE_PATH = "./data/duke.txt";

    /** The storage instance used to load and save tasks to data file. */
    private Storage storage;
    /** List of tasks. */
    private TaskList tasks;
    /** Ui helper class that deals with interactions with the user. */
    private Ui ui;

    /**
     * Constructor for Doemon.
     *
     * @param filePath Path of the file where tasks will be saved to.
     */
    public Doemon(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (TaskDataException tde) {
            ui.showError(tde);
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the Doemon chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputString = ui.readCommand();
                Command c = Parser.parse(inputString);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DoemonException de) {
                ui.showError(de);
            }
        }
    }

    public static void main(String[] args) {
        new Doemon(TASK_FILE_PATH).run();
    }
}
