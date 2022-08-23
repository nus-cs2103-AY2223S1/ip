package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a program called Duke. A <code> Duke </code> is a software which
 * acts as a todolist.
 */
public class Duke {
    private final UI ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for Duke. Initialises UI, Storage and TaskList objects.
     */
    public Duke() {
        this.ui = new UI();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.initialise());
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = Parser.parse(userInput, taskList);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printWithDivider(e.getMessage());
            }
        }
    }

    /**
     * Main method for Duke program.
     * @param args Arguments for main method.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
