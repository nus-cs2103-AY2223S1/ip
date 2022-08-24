package duke;

/**
 * Represents Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Duke with the given file path.
     *
     * @param filePath Path should point to existing saved tasks or where tasks should be saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage, ui);
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = Parser.parse(fullCommand, tasks, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke_tasks").run();
    }
}
