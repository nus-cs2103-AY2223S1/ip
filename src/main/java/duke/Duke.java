package duke;

import duke.command.Command;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError(e);
            this.tasks = new TaskList();
        }
    }

    public void start() {
        ui.welcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                Command c = Parser.parseInput(userCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").start();
    }
}
