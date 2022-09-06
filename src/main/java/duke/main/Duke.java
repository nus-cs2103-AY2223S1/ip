package duke.main;

import java.util.Timer;
import java.util.TimerTask;

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

    private boolean isExit = false;

    /**
     * Constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            assert true: "should not experience an error here";
        }
    }

    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            String response = ui.getOutput();
            ui.resetOutput();
            if (isExit) {
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 800);
                ui.showBye();
                response = ui.getOutput();
                ui.resetOutput();
                return response;
                }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
