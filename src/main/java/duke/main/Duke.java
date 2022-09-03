package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class Duke {

    private static final String saveFilePath = "data/duke.txt";

    /* Task list to store all the tasks. */
    private final TaskList tasks;
    /* Storage object to manage save file. */
    private final Storage storage;

    public Duke() {
        this.storage = new Storage(saveFilePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Returns a string that is used in the app's response dialog, based on user input.
     *
     * @param input User input from text field.
     * @return String to use in Lurch's response dialog.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.getCommand(input);
            String output = command.execute(this.tasks, this.storage);
            return output;
        } catch (DukeException e) {
            return Ui.getErrorMessageString(e);
        }
    }

}