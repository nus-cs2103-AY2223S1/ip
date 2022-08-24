import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

/**
 * Represents a robot that takes in tasks given by user through CLI, and other requests such as
 * listing all tasks, deleting tasks, adding tasks and marking tasks.
 *
 * @author Elgin
 */
public class Duke {
    /** All Tasks */
    private TaskList tasks;

    /** Storage for tasks. */
    private final Storage storage;

    /** Ui for Duke. */
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath Path to storage file from root folder (e.g. 'src/data/duke.txt').
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts serving the user in CLI.
     *
     */
    private void run() {
        this.ui.greetUser();

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = this.ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.showDukeError(e.getMessage());
            } catch (NumberFormatException e) {
                this.ui.showNumberCastError();
            } catch (DateTimeParseException e) {
                this.ui.showInvalidDateError();
            }
        }

        this.storage.save(this.tasks);
    }

    /**
     * Runs when program is first executed.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
