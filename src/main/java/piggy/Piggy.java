package piggy;

/**
 * The main class for the Piggy application.
 */
public class Piggy {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a new Piggy application that reads and writes to a file
     *
     * @param filePath The path of the data file.
     */
    public Piggy(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.readTasks());
        ui = new Ui();
    }

    /**
     * Runs the Piggy application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            isExit = Parser.parse(command, ui, taskList);
            storage.writeTasks(taskList);
        }
        ui.showBye();
    }

    /**
     * Starts the entire application.
     *
     * @param args List of arguments to be passed to the application.
     */
    public static void main(String[] args) {
        new Piggy("./tasks.txt").run();
    }
}
