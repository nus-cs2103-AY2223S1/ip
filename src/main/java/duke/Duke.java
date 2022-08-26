package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.list.TaskList;
import duke.parser.Parser;
import duke.storage.ListLoader;
import duke.ui.Ui;

/**
 * Represents an interactive 'ToDo list' with set commands to add, modify, and remove tasks.
 *
 * @author WR3nd3
 */
public class Duke {
    /** duke.storage.ListLoader used to load and save remaining tasks  */
    private ListLoader storage;

    /** duke.list.TaskList storing current tasks */
    private TaskList tasks;

    /** duke.ui.Ui handling interactions of input and output with users */
    private Ui ui;

    /**
     * Constructs duke.Duke object and its components.
     */
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new ListLoader(tasks);
        try {
            storage.load();
        } catch (DukeException e) {
            System.out.println("hey load error");
            ui.showLine();
            ui.showLoadingError();
            ui.showLine();
            tasks = new TaskList();
            storage = new ListLoader(tasks);
            storage.load();
        }
    }

    /**
     * Executes the running of the duke.Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Provides entry point into duke.Duke program.
     *
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        new Duke().run();
    }
}


