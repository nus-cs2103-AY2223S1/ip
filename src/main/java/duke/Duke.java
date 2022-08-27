package duke;

/**
 * The Duke class encapsulates a Personal Assistant Chatbot named Duke to help keep track of various tasks.
 *
 * @author Jerome Hoo Jun Jie
 */
public class Duke {
    /** The storage to load tasks from the file and saves tasks in the file specified */
    private Storage storage;
    /** A collection to store tasks into a list */
    private TaskList tasks;
    /** A user interface to interact with the user */
    private Ui ui;

    /**
     * Instantiates the Duke object.
     *
     * @param filePath the relative path provided for Duke to load and save files.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke chatbot.
     */
    public void run() {
        ui.greeting();
        while (true) {
            String fullCommand = ui.readCommand();
            Parser p = new Parser(fullCommand, ui);

            if (p.readCommand(tasks)) {
                continue;
            }

            ui.exit();
            storage.save(tasks);
            break;
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
