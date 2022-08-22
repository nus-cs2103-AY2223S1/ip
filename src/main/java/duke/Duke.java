package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.UI;

import java.nio.file.Paths;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;

    public Duke() {
        storage = new Storage(Paths.get(System.getProperty("user.dir"), "data", "data.txt"));
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
}
