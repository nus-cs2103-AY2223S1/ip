package duke;

import duke.command.Command;

/**
 * The main class for the Duke program.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Duke object with specified file path to load and store tasks.
     *
     * @param filePath The file path to the local file responsible for loading and saving.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.printErrorMessage(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        boolean isExit = false;
        this.ui.printGreeting();
        while (!isExit) {
            try {
                String userInput = this.ui.read();
                Command command = Parser.parseInput(userInput);
                command.execute(this.storage, this.taskList, this.ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
