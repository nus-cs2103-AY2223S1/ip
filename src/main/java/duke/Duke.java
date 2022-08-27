package duke;

/**
 * Main class for running the Duke chatbot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    /**
     * Initialises the Duke chatbot, loads tasks from specified filepath if found.
     * @param filepath where tasks are stored in persistent memory.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Begins operation of the Duke chatbot.
     */
    public void run() {
        ui.run(tasks, storage);
    }

    /**
     * Begins operation of the Duke chatbot.
     * @param args Not used.
     */
    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}