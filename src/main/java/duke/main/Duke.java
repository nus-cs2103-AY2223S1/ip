package duke.main;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.tasks.TaskList;

/**
 * Duke is our helper which manages the commands
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    /**
     * The tasklist keeps track of all the tasks added
     */
    private TaskList tasks;

    /**
     * Initialises tasklist based on whether data file specified in filePath is valid
     *
     * @param filePath Filepath where data file is stored in
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Creates new Duke and run it
     *
     * @param args NA
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

    /**
     * Runs Duke as long as no ByeCommand given
     */
    void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command received...");
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
