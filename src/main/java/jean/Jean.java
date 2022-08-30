package jean;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import jean.command.Command;
import jean.exception.JeanException;
import jean.parser.Parser;
import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

/**
 * Main class to execute the program.
 */
public class Jean {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Jean object initialised with its Ui, Storage and Ui object.
     */
    public Jean() {
        this.ui = new Ui();
        this.storage = new Storage("data/list.txt");
        TaskList tasks = null;
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            tasks = new TaskList(storage.load());
        } catch (JeanException e) {
            ui.printMessage(ui.getJeanError(e));
            tasks = new TaskList();
        } catch (IOException e) {
            ui.printMessage(ui.getGeneralError(e));
        }
        this.tasks = tasks;
    }

    /**
     * Runs the Jean object.
     */
    public void run() {
        ui.printMessage(ui.greet());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JeanException e) {
                ui.printMessage(ui.getJeanError(e));
            } catch (IOException | DateTimeParseException e) {
                ui.printMessage(ui.getGeneralError(e));
            }
        }
    }

    /**
     * Launches Jean.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Jean().run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim(), tasks);
            return c.execute(tasks, ui, storage);
        } catch (JeanException e) {
            return ui.getJeanError(e);
        } catch (IOException | DateTimeParseException e) {
            return ui.getGeneralError(e);
        }
    }
}
