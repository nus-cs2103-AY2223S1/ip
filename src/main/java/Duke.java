import Duke.Parser;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

/**
* The Duke class stores Storage and TaskList as parameters
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        String[] seperates = filePath.split("/");
        storage = new Storage(seperates[0], seperates[1]);
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
