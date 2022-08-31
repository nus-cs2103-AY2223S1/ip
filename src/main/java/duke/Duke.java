package duke;

import java.util.ArrayList;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * Duke is a bot that allows you to create a schedule, edit it, and memorises it.
 */
public class Duke {

    /** Name of file where tasks are stored */
    private String fileName;
    /** Storage to be initialised */
    private Storage storage;
    /** TaskList to be initialised */
    private TaskList taskList;
    /** Ui to be initialised */
    private Ui ui;

    /**
     * Constructs Duke with a default file name.
     */
    public Duke() {
        fileName = "data.txt";
        storage = new Storage(fileName);
        ui = new Ui();

        try {
            taskList = storage.loadFromFile();
        } catch (DukeException e) {
            ui.printException(e);
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Constructs Duke with a specified file name.
     *
     * @param fileName Specified name of file.
     */
    public Duke(String fileName) {
        this.fileName = fileName;
        storage = new Storage(fileName);
        ui = new Ui();

        try {
            taskList = storage.loadFromFile();
        } catch (DukeException e) {
            ui.printException(e);
            taskList = new TaskList(new ArrayList<>());
        }
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

    /**
     * Initiate Duke program.
     */
    public void run() {
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
