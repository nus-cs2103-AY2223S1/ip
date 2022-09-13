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
    public void init() {
        this.ui.start();
        this.tasks.loadFromLocalStorage(this.storage);
    }

    public String execCommand(String commandStr) {
        assert (tasks != null);
        assert (storage != null);
        Command c = ui.readCommand(commandStr);
        String returnStr = c.exec(this.tasks);
        this.storage.save(this.tasks);
        return returnStr;
<<<<<<< Updated upstream
=======
    }

    public String execCommand(Command c, TaskList customTaskList) {
        assert tasks != null : "task list is not null";
        assert storage != null : "storage is not null";
        String returnStr = c.exec(this.tasks, customTaskList);
        this.storage.save(this.tasks);
        return returnStr;

    }
>>>>>>> Stashed changes

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.init();
    }

}
