package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Duke {

    private final String SAVED_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    public Duke() {
        this.isExit = false;
        this.storage = new Storage(SAVED_PATH);
        try {
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            this.ui = new Ui(new TaskList());
            ui.showError(e.getMessage());
            exit();
        }
        this.ui = new Ui(this.tasks);
    }

    public boolean exitNow() {
        return this.isExit;
    }

    public void exit() {
        Timer timer = new Timer();
        TimerTask exitApp = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(exitApp, new Date(System.currentTimeMillis() + 3 * 1000));
    }

    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                this.isExit = true;
            }
            return output;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

}
