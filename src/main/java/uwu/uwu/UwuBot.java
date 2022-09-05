package uwu.uwu;

import uwu.command.Command;
import uwu.exception.UwuException;
import uwu.task.TaskList;
import uwu.uwu.Parser;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * The UwuBot program to be executed.
 */
public class UwuBot {
    /** The task list that is stored in the user's hard disk. */
    private Storage storage;

    /** The list where tasks are added to. */
    private TaskList tasks;

    /** The ui to print out UwuBot's response. */
    private static Ui ui = new Ui();

    /**
     * Constructor for UwuBot object.
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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (UwuException e) {
            return e.getMessage();
        }
    }

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
