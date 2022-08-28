import duke.command.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the main class in which
 * the program is run.
 * @author Justin Cheng.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * @param filePath The path of the data file
     *                 in String.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }

    /**
     * Runs the program by iterating inputs and
     * parsing commands, which leads to actions
     * being triggered in the program.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showText(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to start the program.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
