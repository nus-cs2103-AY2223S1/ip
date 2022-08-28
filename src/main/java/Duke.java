import Duke.commands.Command;
import Duke.exceptions.DukeException;
import Duke.parser.Parser;
import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLineBreak();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            finally {
                ui.showLineBreak();
            }
        }
    }

    public static void main(String[] args) {

        new Duke("/Users/iz/Documents/Github/ip/src/main/data/tasks.txt").run();
    }
}
