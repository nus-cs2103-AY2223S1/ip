package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
            storage.load();
        } catch (IOException e) {
            ui.fileLoadError();
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        ui.welcome();


        while (!isExit) {
            String input = ui.requestInput();
            Command command = Parser.parse(input, ui);
            if (command == null) {
                continue;
            }
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
