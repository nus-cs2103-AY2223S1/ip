package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.operations.Parser;
import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;
import seedu.duke.task.Task;

import java.util.ArrayList;

public class Duke {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    Duke(String filename) {
        this.ui = new Ui();
        this.storage = new Storage(filename);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
        }
        this.tasks = new TaskList(tasks);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
