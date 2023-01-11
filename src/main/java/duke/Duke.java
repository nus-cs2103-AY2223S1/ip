package duke;

import duke.command.Command;

/**
 * The main duke program.
 */
public class Duke {

    /**
     * Stores task information in file.
     */
    private Storage storage;
    /**
     * List to track current tasks.
     */
    private TaskList tasks;
    /**
     * Stores task information in file.
     */
    private Storage archiveStorage;
    /**
     * List to track archived tasks.
     */
    private TaskList archiveTasks;
    /**
     * User interface for duke bot.
     */
    private Ui ui;
    /**
     * Indicates when user inputs exit command.
     */
    private boolean isExit = false;

    /**
     * Creates an instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/dukeInfo.txt");
        tasks = new TaskList(storage.load());
        archiveStorage = new Storage("./data/archiveInfo.txt");
        archiveTasks = new TaskList(archiveStorage.load());
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Gets the response from duke, from the execution of the user command.
     *
     * @param input User input.
     * @return String representation of duke's response to user command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isArchive()) {
                c.setArchiveStorage(archiveStorage);
                c.setArchiveTasks(archiveTasks);
            }
            String toReturn = c.execute(tasks, ui, storage);
            this.isExit = c.isExit();
            return toReturn;
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public static void main(String[] args) {
        new Duke();
    }
}
