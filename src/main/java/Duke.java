import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            this.ui = new Ui(tasks);
        } catch (DukeException e) {
            tasks = new TaskList();
            this.ui = new Ui(tasks);
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.getUserInputs();
        storage.updateFile(tasks.getItemList());
    }

    public static void main(String[] args) {
        new Duke("dukeHistory.txt").run();
    }
}
