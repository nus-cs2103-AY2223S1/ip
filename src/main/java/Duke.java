import Duke.Parser;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.printErrorMessage(e.toString());
        }
    }

    public void run() {
        Parser.readLine(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
