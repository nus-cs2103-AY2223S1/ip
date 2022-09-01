package duke;

/**
 * duke program implements a chatbot in the terminal that can respond to
 * programmed commands.
 *
 * @author Sean Lam
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constucts Duke for use
     *
     * @param filePath Location of file storing list of saved tasks
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            this.ui = new Ui(tasks);
        } catch (DukeException e) {
            tasks = new TaskList();
            this.ui = new Ui(tasks);
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.getUserInputs();
        storage.updateFile(tasks.getItemList());
    }

    public String getResponse(String userInput) {
        return ui.getOneInput(userInput);
    }

    public static void main(String[] args) {
        new Duke("dukeHistory.txt").run();
    }
}
