/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

import duke.command.Command;

/**
 * class Duke that runs chat bot Duke.
 */
public class DukePro {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * public constructor for Duke.
     */
    public DukePro() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.home") + "/data/duke.txt");
        this.tasks = new TaskList(storage.loadFromFile());
    }

    public String getResponse(String command) {
        try {
            Command com = Parser.parse(command);
            return com.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Public method to terminate the application.
     */
    public void terminateApplication() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 100);
    }
}
