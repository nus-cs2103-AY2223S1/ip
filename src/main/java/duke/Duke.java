package duke;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException de) {
                ui.showError(de);
            } finally {
                ui.showLine();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
