package yilia;

import java.io.IOException;

import yilia.command.Command;
import yilia.exception.DescriptionEmptyException;
import yilia.exception.KeywordMissingException;
import yilia.exception.NoIndexException;
import yilia.exception.TimeFormatException;
import yilia.exception.YiliaException;
import yilia.task.TaskList;
import yilia.util.Parser;
import yilia.util.Storage;
import yilia.util.Ui;

/**
 * Represents a chat box to complete given commands.
 */
public class Yilia {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    /**
     * Initializes Yilia.
     *
     */
    public Yilia() {
        ui = new Ui();
        storage = new Storage("data/yilia.txt");
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
        } catch (DescriptionEmptyException e) {
            return ui.showError(e.getMessage());
        } catch (KeywordMissingException e) {
            return ui.showError(e.getMessage());
        } catch (NoIndexException e) {
            return ui.showError(e.getMessage());
        } catch (TimeFormatException e) {
            return ui.showError(e.getMessage());
        } catch (YiliaException e) {
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
            } catch (DescriptionEmptyException e) {
                ui.showError(e.getMessage());
            } catch (KeywordMissingException e) {
                ui.showError(e.getMessage());
            } catch (NoIndexException e) {
                ui.showError(e.getMessage());
            } catch (TimeFormatException e) {
                ui.showError(e.getMessage());
            } catch (YiliaException e) {
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
