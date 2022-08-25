package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.task.TasksList;

/**
 * Represents Duke Bot that functions and responds according to user's commands.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TasksList tasksList;

    /**
     * Creates a Duke bot and initialise the Storage, Ui and TasksList.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.storage.initialiseSaveFile();
            this.tasksList = storage.createTaskList();
            this.ui.showMessage("Loading from save...\n");
        } catch (DukeException e) {
            this.ui.showError(e);
            tasksList = new TasksList();
        }
    }

    /**
     * Runs the Duke bot.
     */
    public void run() {
        this.ui.showGreeting();
        while (this.ui.isActive()) {
            try {
                String input = this.ui.getUserCommand();
                Command command = Parser.parse(input, this.storage, this.tasksList, this.ui);
                command.execute();
            } catch (DukeException e) {
                this.ui.showError(e);
            } finally {
                System.out.println();
            }
        }
    }

    /**
     * Returns the String representation of Duke.
     *
     * @return The String representation of Duke.
     */
    @Override
    public String toString() {
        return "Duke";
    }

    /**
     * Creates and initiates Duke to run.
     *
     * @param args Ignore.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
