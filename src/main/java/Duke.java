public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private static final String filePath = "./data/duke.txt";
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            try {
                storage.createFile();
            } catch (DukeException ex) {
                ui.showError(e.getMessage());
            }
        }
    }
    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String response = ui.readCommand();
                Command c = Parser.parse(response);
                ui.lineBreak();
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.lineBreak();
            }
        }
    }
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
