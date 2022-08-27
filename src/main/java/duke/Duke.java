package duke;
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
        ui.greeting();
        while (true) {
            String fullCommand = ui.readCommand();
            Parser p = new Parser(fullCommand, ui);
            if (p.readCommand(tasks)) {
                continue;
            }
            ui.exit();
            storage.save(tasks);
            break;
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
