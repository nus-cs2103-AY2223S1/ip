/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke;

import duke.command.*;

/**
 * class Duke that runs chat bot Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * public constructor for Duke.
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadfromFile());
    }

    /**
     * Driver code for Duke.
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        new Duke("D:\\NUS\\Tasks.txt").run();
    }

    /**
     * class Method run that runs Duke when instance created.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.readLine();
                Command com = Parser.parse(command);
                com.execute(tasks, ui, storage);
                isExit = com.isExit();
            } catch (DukeException e) {
                ui.printMsg(e.getMessage());
            }
        }
    }
}
