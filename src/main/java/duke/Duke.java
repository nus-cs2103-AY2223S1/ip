package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
            this.ui = new Ui(storage, tasks);
        } catch(DukeException e) {
            tasks = new TaskList();
            this.ui = new Ui(storage, tasks);
            ui.showLoadingError();
        }


    }

    public void run() {
        ui.start();
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}



