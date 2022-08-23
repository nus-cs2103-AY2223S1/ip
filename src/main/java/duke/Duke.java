package duke;

import duke.commands.Command;
import duke.task.TaskList;

public class Duke {
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String fileName) {
        this.ui = new Ui();

        Storage storage;
        try {
            storage = new Storage(fileName);
        } catch (DukeException e) {
            ui.showLoadingError();
            storage = null;
        }
        this.tasks = new TaskList(storage);
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
