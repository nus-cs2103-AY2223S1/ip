package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.UI;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;

    public Duke(Path path) {
        storage = new Storage(path);
        tasks = new TaskList(storage.load());
        ui = new UI(System.in, System.out);
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = Parser.parseCommand(ui.read());
                command.execute(storage, ui, tasks);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get(System.getProperty("user.dir"), "data", "data.txt")).run();
    }
}
