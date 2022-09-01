package duke;

import java.util.ArrayList;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.gui.Main;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;
import javafx.application.Application;


/**
 * Duke is a bot that allows you to create a schedule, edit it, and memorises it.
 */
public class Duke {
    /** Name of file where tasks are stored */
    private final String fileName;
    /** Storage to be initialised */
    private final Storage storage;
    /** TaskList to be initialised */
    private TaskList taskList;

    /**
     * Constructs Duke with a default file name.
     */
    public Duke() {
        fileName = "data.txt";
        storage = new Storage(fileName);

        try {
            taskList = storage.loadFromFile();
        } catch (DukeException e) {
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

        try {
            taskList = storage.loadFromFile();
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * This is the main method where the program runs from.
     *
     * @param args Unused parameter
     */
    public static void main(String... args) {
        Application.launch(Main.class, args);
    }

    /**
     * Returns response from Duke according to the input.
     *
     * @param input Input from user.
     * @return String response from Duke.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(taskList, storage);
        } catch (DukeException de) {
            return Ui.formatExceptionString(de);
        }
    }
}
