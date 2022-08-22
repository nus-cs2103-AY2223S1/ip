package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.emptyLine();
            }
        }
    }

    public static void main(String[] args) {
        Path storagePath = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        new Duke(storagePath).run();
    }
}
