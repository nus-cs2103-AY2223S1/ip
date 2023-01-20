package uwu.uwu;

import uwu.command.Command;
import uwu.exception.UwuException;
import uwu.task.TaskList;

/**
 * The UwuBot program to be executed.
 */
public class UwuBot {
    /** The ui to print out UwuBot's response. */
    private static Ui ui = new Ui();

    /** The task list that is stored in the user's hard disk. */
    private Storage storage;

    /** The list where tasks are added to. */
    private TaskList tasks;

    /**
     * Constructs an UwuBot object.
     */
    public UwuBot() {
        storage = new Storage("data/taskList.txt");
        try {
            tasks = storage.load();
        } catch (UwuException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Gets the response from UwuBot based on user's input.
     *
     * @param input The user's input.
     * @return UwuBot's response to user's input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (UwuException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if the UwuBot program has ended.
     *
     * @param input The user input.
     * @return true if the command exits the UwuBot program.
     *         false if the command does not exit the UwuBot program.
     */
    public boolean isEnd(String input) {
        try {
            Command c = Parser.parse(input);
            return c.isExit();
        } catch (UwuException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
