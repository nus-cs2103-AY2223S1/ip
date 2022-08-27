package duke;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.run(tasks, storage);
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}