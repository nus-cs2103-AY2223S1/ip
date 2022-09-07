package duke;

public class Duke {
    private final static String PATH = "../data/duke.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }

    public void run() {

        ui.showWelcome();
        new Parser(this.tasks).parser();
        storage.save(this.tasks);
        ui.farewell();
    }
}
