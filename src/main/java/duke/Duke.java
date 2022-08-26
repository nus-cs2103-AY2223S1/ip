package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
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

    /** Constructs a Duke object with the default data file name. */
    public Duke() {
        fileName = "data.txt";
    }

    /**
     * Constructs a Duke object with a custom data file name.
     *
     * @param fileName The name of the data file to access.
     */
    public Duke(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This is the main method where the program runs from.
     *
     * @param args Unused parameter
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /** Starts up Duke. */
    public void run() {
        Storage storage = new Storage(fileName);
        Ui ui = new Ui();
        TaskList taskList;

        try {
            taskList = storage.loadFromFile();
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<>());
            ui.sayExceptionMessage(e);
        }

        ui.sayGreet();

        while (ui.canContinue()) {
            try {
                String str = ui.readCommand();
                Command command = Parser.parseCommand(str);
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.sayExceptionMessage(e);
            }
        }
    }
}

