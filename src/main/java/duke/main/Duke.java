package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.MissingTimeException;

import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String path) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(path);
        } catch (DukeException e) {
            this.ui.showSavingError();
        }
        this.taskList = new TaskList(this.storage.load());
    }

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
