package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.util.ArrayList;

/**
 * Duke is an interactive robot that helps to keep track of your tasks.
 */
public class Duke {

    /** Stores the name of the file where the tasks are stored on. */
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

    /** Starts up Duke. */
    public void run() {
        Storage storage = new Storage(this.fileName);
        Ui ui = new Ui();
        TaskList taskList;

        try {
            taskList = storage.readFromFile();
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<Task>());
            ui.handleException(e);
        }

        ui.greet();

        while (ui.isContinue()) {
            try {
                String str = ui.readCommand();
                Command command = Parser.parseCommand(str);
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.handleException(e);
            }
        }
    }
}

