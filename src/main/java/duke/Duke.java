package duke;


/**
 * Represents the DUKE chatbot each <code>Duke</code> object contains a <code>Storage</code>,
 * a <code>TaskList</code> and a <code>Ui</code>
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke Class
     * @param filepath
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
            this.ui = new Ui(storage, tasks);
        } catch (DukeException e) {
            tasks = new TaskList();
            this.ui = new Ui(storage, tasks);
            ui.showLoadingError();
        }


    }

    /**
     * Starts the Duke chatbot
     */
    public void run() {
        ui.start();
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}



