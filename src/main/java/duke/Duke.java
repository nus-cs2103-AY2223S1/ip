package duke;

import duke.command.Command;

public class Duke {

    public Ui ui;
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
    public void init() {
        this.ui.start();
        this.tasks.loadFromLocalStorage(this.storage);
    }

    public String execCommand(Command c) {
        assert (tasks != null);
        assert (storage != null);
        String returnStr = c.exec(this.tasks);
        this.storage.save(this.tasks);
        return returnStr;

    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.init();
    }

}
