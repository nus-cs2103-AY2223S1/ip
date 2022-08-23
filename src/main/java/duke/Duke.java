/**
 * Project done by Hong Jin.
 */
package duke;

import duke.command.*;
import duke.DukeException;
import duke.*;

/**
 * Main class that runs chat bot Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static final String initText = "Hello! I'm Duke\n    What can I do for you?";
    public static final String endText = "Bye bye! Hope to see you again soon!";

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadfromFile());
    }


    public static void main(String[] args) throws DukeException {
        new Duke("D:\\NUS\\Tasks.txt").run();
    }


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
