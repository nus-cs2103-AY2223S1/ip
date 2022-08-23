/**
 * Chatbot class that helps a person to keep track of various things.
 */
public class Zeus {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of chatbot class.
     */
    public Zeus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ZeusException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.generateLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ZeusException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.generateLine();
            }
        }
    }

    /**
     * Main method that initialises chatbot and starts the chat.
     *
     * @param args a String array of input arguments
     */
    public static void main(String[] args) {
        new Zeus("data/tasks.txt").run();
    }
}
