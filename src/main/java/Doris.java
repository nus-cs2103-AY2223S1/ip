import command.Command;
import exception.DorisException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Doris {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Doris(String filePath) throws DorisException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while(!isExit) {
            try {
                Command command = Parser.parse(ui.readCommand());
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DorisException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) throws DorisException {
        new Doris("data/tasks.txt").run();
    }
}