import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();

        storage.readSavedTasks();
    }

    public void run() {
        ui.bootUpDuke();
        loopInputRead();
    }

    private void loopInputRead() {
        Parser parser = new Parser();
        parser.handleInput();

    }

    public static void main(String[] args) {
        Duke chatBotInstance = new Duke();
        chatBotInstance.run();
    }
}
