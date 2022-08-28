package duke;

public class Jamie {
    protected final TaskList tasks;
    protected final Ui ui;
    private final Storage storage;


    public Jamie(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }
    public void run() {
        ui.welcome();
        Parser parser = new Parser(this);
        parser.start();
        ui.bye();
    }

    public static void main(String[] args) {
        new Jamie("Data/JamieTasks.txt").run();
    }
}
