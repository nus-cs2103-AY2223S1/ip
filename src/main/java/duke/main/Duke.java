package duke.main;

import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.MissingArgumentException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.MissingTimeException;

/**
 * Main class for Duke. Contains objects handling storage, the task list and Ui.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new Duke object which reads the save from a file at path.
     * @param path The path to read the save from.
     */
    public Duke(String path) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(path);
        } catch (DukeException e) {
            this.ui.showSavingError();
        }
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Starts the main logic of the Duke object.
     */
    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        this.ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            this.ui.showYou();
            String fullCommand = this.ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (NumberFormatException e) {
                this.ui.showNotANumber();
            } catch (MissingIndexException e) {
                this.ui.showMissingIndex();
            } catch (MissingDescriptionException e) {
                this.ui.showMissingDescription();
            } catch (MissingTimeException e) {
                this.ui.showMissingTime();
            } catch (DateTimeParseException e) {
                this.ui.showInvalidTime();
            } catch (MissingArgumentException e) {
                this.ui.showMissingArgument();
            }

            try {
                this.storage.save(this.taskList);
            } catch (DukeException e) {
                this.ui.showSavingError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Storage.FILE_PATH).run();
    }
}
