package sally;

import java.io.File;
import java.io.IOException;

import sally.command.Command;
import sally.exception.SallyException;
import sally.parser.Parser;
import sally.storage.Storage;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * Main class for Sally
 *
 * @author liviamil
 */

public class Sally {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Sally
     *
     * @param filePath Path to file for storage.
     */
    public Sally(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SallyException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Main method to run Sally
     *
     * @param args
     */
    public static void main(String[] args) {
        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File saved = new File("data/Sally.txt");
        if (!saved.exists()) {
            try {
                saved.createNewFile();
            } catch (IOException e) {
                System.out.println("Oops! Unable to create a new file.");
            }
        }

        new Sally("data/Sally.txt").run();
    }

    /**
     * Runs the Sally bot
     */
    public void run() {
        try {
            storage.load();
        } catch (SallyException e) {
            ui.printWithBorder(e.getMessage());
        }

        ui.showGreeting();
        boolean isBye = false;

        while (!isBye) {
            try {
                String stringCommand = ui.getUserCommand();
                Command command = Parser.parseCommand(stringCommand);
                command.execute(tasks, ui, storage);
                isBye = command.isBye();
            } catch (SallyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

}