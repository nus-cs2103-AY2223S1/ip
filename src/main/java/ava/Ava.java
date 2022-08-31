package ava;

import java.io.IOException;

import ava.exception.NoCommandException;
import ava.processor.Parser;
import ava.processor.Storage;
import ava.processor.TaskList;
import ava.task.Task;

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
     * @throws NoCommandException If there are no commands.
     */
    public String run(String input) {
        String output = "";
        try {
            Task t = Parser.parse(input, tasks);
            output = t.execute(tasks, ui, storage);
            isBye = t.isBye();
        } catch (NoCommandException e) {
            e.printStackTrace();
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
