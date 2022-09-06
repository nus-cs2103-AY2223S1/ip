package ava;

import java.io.IOException;

import ava.command.Command;
import ava.exception.AvaException;
import ava.util.Parser;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * This is the Main Class that contains the Main method.
 */
public class Ava {
    private static final String FILE_PATH = "data/ava.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isBye;

    /** Constructor of Ava. */
    public Ava() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.read());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic of the program.
     *
     * @return The response from executing the command.
     */
    public String run(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input, tasks);
            output = c.execute(tasks, ui, storage);
            isBye = c.isBye();
        } catch (AvaException e) {
            output = e.getMessage();
        }
        assert !output.equals("");
        return output;
    }

    /**
     * Checks if the last user command is an exit command.
     *
     * @return True if the last user command is an exit command, false otherwise.
     */
    public boolean isBye() {
        return isBye;
    }
}
