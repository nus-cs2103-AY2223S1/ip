package yilia;

import java.io.IOException;

import yilia.command.Command;
import yilia.task.TaskList;
import yilia.util.Parser;
import yilia.util.Storage;
import yilia.util.Ui;

/**
 * Represents a chat box to complete given commands.
 */
public class Yilia {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;
    /**
     * Initializes Yilia.
     *
     */
    public Yilia() {
        ui = new Ui();
        String filePath = System.getProperty("os.name").startsWith("Windows")
                          ? "data\\yilia.txt"
                          : "data/yilia.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
    /**
     * Runs the main body of the chat box.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Yilia().run();
    }
}
