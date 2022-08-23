import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import exceptions.DukeException;
import command.Command;
import utility.Parser;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        try {
            ui = new UI();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.syncArrayList());
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.greetUser();
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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
