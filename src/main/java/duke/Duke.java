package duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage, ui);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = Parser.parse(fullCommand, tasks, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke_tasks").run();
    }
}
