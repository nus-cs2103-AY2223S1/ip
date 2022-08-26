import Command.Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Parser;
import Duke.TaskList;
import Duke.Storage;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private DukeUi ui;

    public Duke(String filePath) {
        ui = new DukeUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            DukeUi.sendMessage("load complete!");
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                } else {
                    continue;
                }
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}