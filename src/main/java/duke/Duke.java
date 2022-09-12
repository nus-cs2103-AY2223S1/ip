package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private final String SAVED_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke() {
        this.storage = new Storage(SAVED_PATH);
        try {
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            this.ui = new Ui(new TaskList());
            ui.showError(e.getMessage());
            System.exit(0);
        }
        this.ui = new Ui(this.tasks);
    }

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
    }

    public static void main(String[] args) {
        Duke sampleDuke = new Duke();
        sampleDuke.run();
    }

}
