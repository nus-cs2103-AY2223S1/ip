package uwu;

import uwu.command.Command;
import uwu.exception.UwuException;
import uwu.task.TaskList;

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
     *
     * @param filePath The path to the file where the task list is stored.
     */
    public UwuBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (UwuException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the UwuBot program.
     */
    public void run() {
        ui.showLine();
        ui.greetUsers();
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (UwuException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new UwuBot("data/taskList.txt").run();
    }
}
