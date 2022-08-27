import components.Parser;
import components.Storage;
import components.TaskList;
import components.Ui;
import components.DukeException;
import components.Event;
import components.Todo;
import components.Task;
import components.Deadline;
import java.util.Scanner;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        tasks.setStorage(storage);
        Parser.setUi(ui);
        Parser.setTaskList(tasks);
        Scanner sc = new Scanner(System.in);
        ui.getPrompt(sc);
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}