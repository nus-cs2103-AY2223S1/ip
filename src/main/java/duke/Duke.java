package duke;

public class Duke {
    protected static boolean terminate = false;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath, "data/temp.txt");
        ui = new Ui();
        tasks = new TaskList(ui, storage.startUp(), storage);
        parser = new Parser(tasks, ui);
    }

    public void run() {
        ui.greet();
        parser.takeUserInput();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
