package duke.main;

import duke.command.Command;
import duke.errors.DukeException;
import duke.task.TaskList;

/**
 * Represents the Duke programme. Carries out the commands and handles exceptions
 */
public class Duke {
    private static final String FILEPATH = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke
     * @param filePath String of file path of text file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs main program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showBye();
    };

    /**
     * Runs main method
     * @param args
     */
    public static void main(String[] args) {
        new Duke(FILEPATH).run();
    }
}
