package duke;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException err) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    private void run() {
        ui.intro();
        //int id = 0;
        boolean isBye = false;
        while (!isBye) {
            try {
                String commandText = ui.readInput();
                Command command = Parser.parse(commandText);
                command.execute(tasks, ui, storage);
                isBye = command.isBye();
            } catch (DukeException err) {
                System.out.println(err);
            } finally {
                ui.lineBreak();
            }
        }
    }
}
