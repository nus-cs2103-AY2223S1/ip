package tako;

import java.io.IOException;

import tako.command.Command;

/**
 * A chatbot named Tako that
 * supports various tasks.
 *
 * @author Alvin Tan Fu Long
 */
public class Tako {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Tako with the file path to save data at.
     *
     * @param filePath File path to save data at.
     */
    public Tako(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts chatting with the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TakoException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs the Tako chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Tako("data/tasks.txt").run();
    }
}
