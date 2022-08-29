import justin.command.Command;
import justin.DukeException;
import justin.Parser;
import justin.Storage;
import justin.TaskList;
import justin.Ui;

/**
 * Represents the main class in which
 * the program is run.
 * @author Justin Cheng.
 */
public class JustinBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * @param filePath The path of the data file
     *                 in String.
     */
    public JustinBot(String filePath) {
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
        new JustinBot("data/justin.txt").run();
    }
}
