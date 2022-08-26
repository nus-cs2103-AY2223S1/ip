package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.DukeException;
import duke.ui.Parser;
import duke.ui.Ui;

/**
 * Main class for Duke program.
 * Keeps track of tasks for user.
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean isEnd;

    /**
     * Constructor for Duke class to initialise program.
     * Initialises the TaskList, Ui, and Storage.
     */
    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage();
            this.tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            this.ui.printException(e);
        } catch (IOException e) {
            this.ui.printException(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Main driver function for Duke program.
     */
    private void run() {
        this.ui.printIntro();
        while (!this.isEnd) {
            try {
                String line = this.ui.nextLine();
                Command command = Parser.parse(line);
                this.ui.printLine();
                command.run(this.tasks, this.ui, this.storage);
                this.isEnd = command.getIsEnd();
            } catch (DukeException | IOException | DateTimeParseException e) {
                this.ui.printException(e);
            } finally {
                this.ui.printLine();
            }
        }
        this.ui.close();
    }

    /**
     * Main that runs the program.
     * @param args
     */
    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}
