package duke;

import duke.command.Command;

/**
 * Main class that is executed.
 */
public class Duke {

    public static final String FOLDER_LOCATION = "data";
    public static final String FILE_LOCATION = "data/duke.txt";

    private boolean isLoaded;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Duke Object, initializing Ui, Storage and TaskList.
     *
     * @param filePath Path to save file.
     * @param folderPath Path to folder containing save file.
     */
    public Duke(String filePath, String folderPath) {
        storage = new Storage(filePath, folderPath);
        try {
            tasks = new TaskList(storage.load());
            isLoaded = true;
        } catch (DukeException e) {
            tasks = new TaskList();
            isLoaded = false;
        }
    }

    /**
     * Creates a new Duke Object with the default folder and file location.
     */
    public Duke() {
        this(FILE_LOCATION, FOLDER_LOCATION);
    }

    public boolean getLoaded() {
        return isLoaded;
    }

    /**
     * Processes user input by parsing the input and executing given command.
     * Saves the task list after given command.
     * @param input
     * @return Result of command to be printed by Duke.
     */
    public String getResponse(String input) throws Exception {
        Command c = Parser.parse(input);
        String res = c.execute(tasks, storage);
        storage.save(tasks);
        return res;
    }
}
