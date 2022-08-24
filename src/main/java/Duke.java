import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    
    private Storage storage;
    private  TaskList taskList;
    private Ui ui;

    public Duke (String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.toString());
            taskList = new TaskList();
        }
    }

    public void run() throws DukeException, IOException {
        ui.showGreetMsg();
        while (true) {
            ui.showLine();
            String input = ui.readCommand();
            Command currCmd = ui.run(input);
            currCmd.execute(ui, taskList);

            if (currCmd instanceof ExitCommand) {
                break;
            }
        }
        storage.writeFile(taskList);
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./data/duke.txt").run();
    }
}
