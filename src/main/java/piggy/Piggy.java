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
     */
    public Piggy() {
        storage = new Storage("./tasks.txt");
        taskList = new TaskList(storage.readTasks());
        ui = new Ui();
        assert ui != null;
        assert storage != null;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String res = Parser.parse(input, ui, taskList);
        storage.writeTasks(taskList);
        return res;
    }
}
