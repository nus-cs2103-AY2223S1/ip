package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.util.ArrayList;

public class Duke {

    private String fileName;

    public Duke() {
        this.fileName = "data.txt";
    }

    public Duke(String fileName) {
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Storage storage = new Storage(this.fileName);
        Ui ui = new Ui();
        TaskList taskList;

        try {
            taskList = storage.loadFromFile();
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<Task>());
            ui.printException(e);
        }

        ui.printGreeting();

        while (ui.canContinue()) {
            try {
                String str = ui.readCommand();
                Command command = Parser.parseCommand(str);
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
    }
}
