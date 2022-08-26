import duke.Command;
import duke.DukeConstants;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;
public class DukeBot {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public DukeBot() {
        ui = new Ui();
        storage = new Storage(DukeConstants.FILENAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError("file not found!");
            taskList = new TaskList();
        }

    }

    public static void main(String[] args) {
        new DukeBot().run();
    }

    public void run() {
        String reply = "";
        Parser parser = new Parser();
        boolean isExit = false;
        ui.showWelcome();

        while(!isExit) {
            try {
                reply = ui.readCommand();
                Command c = parser.parse(taskList, reply, ui);
                taskList = c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
