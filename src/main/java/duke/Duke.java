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

    //@@author Sampy147-reused
    //Reused from https://github.com/nus-cs2103-AY1920S1/duke/pull/266/files
    //with minor modifications
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

    //@@author Sampy147-reused
    //Reused from https://stackoverflow.com/questions/15747277/how-to-make-java-program-exit-after-a-couple-of-seconds
    // with minor modifications
    public void exit() {
        Timer timer = new Timer();
        TimerTask exitApplication = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(exitApplication, new Date(System.currentTimeMillis() + 3 * 1000));
    }
    //@@author

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
