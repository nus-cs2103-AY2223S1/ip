import java.io.*;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        if (storage.fileExists()) {
            tasks = new TaskList(storage.load());
        } else {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(tasks, fullCommand);
                c.execute(tasks, ui, storage);
                isExit = Command.isExit;
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Duke("tasks.dat").run();
    }
}
