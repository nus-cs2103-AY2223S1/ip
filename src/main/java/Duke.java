import java.io.IOException;

/**
 * Represents a Duke class
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Duke {

    /**
     * Represents a storage for texts.
     */
    private final Storage storage;

    /**
     * Represents a list of tasks.
     */
    private final TaskManager taskManager;

    /**
     * Represents a User interface.
     */
    private final Ui ui;

    /**
     * Represents a constructor method for Duke class
     * @param filepath
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskManager = new TaskManager(storage.load());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskManager, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method.
     *
     * @param args given arguments
     */
    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();

    }
}
