package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final Path PATH = Paths.get(
            System.getProperty("user.home"),
            "duke",
            "tasks.txt"
    );
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    private Duke(Path path) {
        this.storage = new Storage(path);
        this.ui = new Ui();
        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (IOException e) {
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, storage, ui);
                if (command.isTerminal()) {
                    return;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage() + ".");
            } catch (Exception e) {
                ui.showError(e.toString());
            }
        }
    }
}
