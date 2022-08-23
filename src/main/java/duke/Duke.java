package duke;

import duke.command.Command;
import duke.util.DataFileCorruptedException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.UI;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private final Storage storage;
    private final UI ui;
    private TaskList tasks;

    public Duke(Path path) {
        storage = new Storage(path);
        ui = new UI(System.in, System.out);
        try {
            tasks = new TaskList(storage.load());
        } catch (DataFileCorruptedException e) {
            ui.print(e.getMessage());
            if (ui.readYesNoResponse("Do you want to reset the data file?")) {
                tasks = new TaskList();
                storage.save(tasks);
            } else {
                ui.exit();
                System.exit(0);
            }
        }
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
