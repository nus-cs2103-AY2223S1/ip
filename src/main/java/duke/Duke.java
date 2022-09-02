package duke;

import duke.command.Command;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private LocalStorage storage;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new LocalStorage("./data/saveFile.json");
    }

    /**
     * Run the Duke app.
     */
    public void run() {
        this.ui.start();
        this.tasks.loadFromLocalStorage(this.storage);

        while (this.ui.isOpen()) {
            Command c = ui.readCommand();
            ui.printWithHorizontalRule(c.exec(this.tasks));
            this.storage.save(this.tasks);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
