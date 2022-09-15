package duke;

import java.util.ArrayList;
import duke.commands.Command;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws DukeException, IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.print(ui.showError(e));
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.print(ui.showLine()); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(ui.showError(e));
            } finally {
                ui.print(ui.showLine());
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Duke("./data/tasks.txt").run();
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
