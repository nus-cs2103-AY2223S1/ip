package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public final static String DIRECTORY = "./data/";
    public final static String FILENAME = "todo.txt";


    Duke(String directory, String fileName) {
        ui = new Ui();
        try {
            storage = new Storage(directory, fileName);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showPrompt();
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(DIRECTORY, FILENAME);
        duke.run();
    }
}