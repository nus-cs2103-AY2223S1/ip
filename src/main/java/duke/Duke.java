package duke;

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
