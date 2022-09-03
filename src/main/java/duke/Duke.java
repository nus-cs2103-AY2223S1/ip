package duke;

import duke.ui.Launcher;

/**
 * {@code Duke} is the main class of this program, and it manages the storage and the ui.
 *
 * @author ngshijun
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for {@code Duke} to initialize the ui and the storage
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("tasks.dat");
        if (storage.isFileExists()) {
            tasks = new TaskList(storage.load());
        } else {
            storage.createNewFile();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }

    public String getResponse(String input) throws DukeException {
        return Parser.parse(input).execute(tasks, ui, storage);
    }
}
