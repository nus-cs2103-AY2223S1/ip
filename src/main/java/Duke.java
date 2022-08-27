public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
        ui.run(tasks);
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}